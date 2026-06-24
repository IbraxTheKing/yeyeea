package ax.ibr.yeyeea.restserver.resources

import ax.ibr.utils.LogLevel
import ax.ibr.utils.Logger
import ax.ibr.utils.rest.RequiresAuth
import ax.ibr.utils.rest.RequiresRole
import ax.ibr.yeyeea.common.entities.Product
import ax.ibr.yeyeea.common.entities.Category
import ax.ibr.yeyeea.business.implementations.BusinessFactory
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType


@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ProductResource {

    private val service = BusinessFactory().getProductService()


    @GET
    fun getAll(): List<Product> {
        return service.getAll()
    }


    @GET
    @Path("/{id}")
    fun getById(
        @PathParam("id") id: Long
    ): Product? {
        return service.getById(id)
    }


    @POST
    fun add(product: Product) {
        service.add(product)

    }


    @PUT
    @Path("/{id}")
    @RequiresAuth(roles = ["ADMIN"], allowOwner = true)
    fun updateById(
        @PathParam("id") id: Long,
        product: Product
    ) {
        product.id = id
        service.update(product)

    }


    @DELETE
    @Path("/{id}")
    @RequiresAuth(roles = ["ADMIN"], allowOwner = true)
    fun removeById(
        @PathParam("id") id: Long
    ) {
        val product = service.getById(id)

        if (product != null) {
            service.remove(product)

        }
    }


    @GET
    @Path("/name/{name}")
    fun getByName(
        @PathParam("name") name: String
    ): List<Product> {
        return service.getByName(name)
    }


    @GET
    @Path("/category/{id}")
    fun getByCategoryId(
        @PathParam("id") id: Long
    ): List<Product> {

        val category = Category().apply {
            this.id = id
        }

        return service.getByCategory(category)
    }


    @GET
    @Path("/price/{price}")
    fun getByPrice(
        @PathParam("price") price: Float
    ): List<Product> {
        return service.getByPrice(price)
    }


    @GET
    @Path("/price-range/{min}/{max}")
    fun getByPriceRange(
        @PathParam("min") min: Float,
        @PathParam("max") max: Float
    ): List<Product> {

        val range = min..max

        return service.getByPriceRange(range)
    }
}