package resources

import entities.Category
import implementations.BusinessFactory
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import services.CategoryService

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class CategoryResource : CategoryService {

    private val service = BusinessFactory().getCategoryService()

    @GET
    override fun getAll(): List<Category> {
        return service.getAll()
    }

    @GET
    @Path("/id/{id}")
    override fun getById(@PathParam("id") id: Long): Category? {
        return service.getById(id)
    }

    @POST
    override fun add(t: Category) {
        service.add(t)
    }
    @PUT
    override fun update(t: Category) {
        service.update(t)
    }

    @DELETE
    override fun remove(t: Category) {
        service.remove(t)
    }

    @GET
    @Path("/subcategory/{subcategory}")
    override fun getSubCategories(category: Category): List<Category>? {
        return service.getSubCategories(category)
    }

    @GET
    @Path("/parentCategory/{parentCategory}")
    override fun getParentCategories(category: Category): List<Category>? {
        return service.getParentCategories(category)
    }
}