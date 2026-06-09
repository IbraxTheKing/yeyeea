package resources

import entities.Category
import entities.Product
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
import services.ProductService

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ProductResource : ProductService {

    private val service = BusinessFactory().getProductService()

    @GET
    override fun getAll(): List<Product> {
        return service.getAll()
    }

    @GET
    @Path("/id/{id}")
    override fun getById(@PathParam("id") id: Long): Product? {
        return service.getById(id)
    }

    @POST
    override fun add(t: Product) {
        service.add(t)
    }
    @PUT
    override fun update(t: Product) {
        service.update(t)
    }

    @DELETE
    override fun remove(t: Product) {
        service.remove(t)
    }

    @GET
    override fun getByName(name: String): List<Product> {
        return service.getByName(name)
    }

    @GET
    override fun getByCategory(category: Category): List<Product> {
        return service.getByCategory(category)
    }

    @GET
    override fun getByPrice(price: Float): List<Product> {
        return service.getByPrice(price)
    }

    @GET
    override fun getByPriceRange(range: ClosedRange<Float>): List<Product> {
        return service.getByPriceRange(range)
    }


}