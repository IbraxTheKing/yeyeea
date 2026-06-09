package ax.ibr.yeyeea.restserver.resources

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import ax.ibr.yeyeea.common.entities.User
import ax.ibr.yeyeea.business.implementations.BusinessFactory
import ax.ibr.yeyeea.common.services.UserService

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class UserResource : UserService {

    private val service = BusinessFactory().getUserService()

    @GET
    override fun getAll(): List<User> {
        return service.getAll()
    }

    @GET
    @Path("/id/{id}")
    override fun getById(@PathParam("id") id: Long): User? {
        return service.getById(id)
    }

    @POST
    override fun add(t: User) {
        service.add(t)
    }
    @PUT
    override fun update(t: User) {
        service.update(t)
    }

    @DELETE
    override fun remove(t: User) {
        service.remove(t)
    }

    @GET
    @Path("/username/{username}")
    override fun getByUsername(@PathParam("username") username: String): List<User> {
        return service.getByUsername(username)
    }

    @GET
    @Path("/email/{email}")
    override fun getByEmail(@PathParam("email") email: String): List<User> {
        return service.getByEmail(email)
    }

}