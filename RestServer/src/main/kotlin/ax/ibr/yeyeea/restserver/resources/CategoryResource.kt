package ax.ibr.yeyeea.restserver.resources

import ax.ibr.utils.rest.RequiresAuth
import ax.ibr.utils.rest.RequiresRole
import ax.ibr.yeyeea.common.entities.Category
import ax.ibr.yeyeea.business.implementations.BusinessFactory
import ax.ibr.yeyeea.common.entities.UserType
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class CategoryResource {

    private val service = BusinessFactory().getCategoryService()

    @GET
    fun getAll(): List<Category> {
        return service.getAll()
    }

    @GET
    @Path("/{id}")
    fun getById(
        @PathParam("id") id: Long
    ): Category? {
        return service.getById(id)
    }

    @POST
    @RequiresRole("ADMIN")
    fun add(category: Category) {
        service.add(category)

    }


    @PUT
    @Path("/{id}")
    @RequiresRole("ADMIN")
    fun updateById(
        @PathParam("id") id: Long,
        category: Category
    ) {
        category.id = id
        service.update(category)

    }


    @DELETE
    @Path("/{id}")
    @RequiresRole("ADMIN")
    fun removeById(
        @PathParam("id") id: Long
    ) {
        val category = service.getById(id)

        if (category != null) {
            service.remove(category)

        }
    }


    @GET
    @Path("/{id}/subcategories")
    fun getSubCategories(
        @PathParam("id") id: Long
    ): List<Category>? {

        val category = service.getById(id)
            ?: return emptyList()

        return service.getSubCategories(category)
    }


    @GET
    @Path("/{id}/parent")
    fun getParentCategories(
        @PathParam("id") id: Long
    ): List<Category>? {

        val category = service.getById(id)
            ?: return emptyList()

        return service.getParentCategories(category)
    }
}