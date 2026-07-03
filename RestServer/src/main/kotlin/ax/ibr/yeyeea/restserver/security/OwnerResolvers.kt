package ax.ibr.yeyeea.restserver.security

import ax.ibr.utils.rest.OwnerResolver
import ax.ibr.yeyeea.business.implementations.BusinessFactory

/** UserResource : l'id du chemin EST directement l'id de l'utilisateur. */
object UserOwnerResolver : OwnerResolver {
    override fun isOwner(pathId: Long, authUserId: Long): Boolean =
        pathId == authUserId
}

/** ProductResource : l'id du chemin est un produit -> on vérifie product.vendor.id. */
object ProductOwnerResolver : OwnerResolver {
    override fun isOwner(pathId: Long, authUserId: Long): Boolean =
        BusinessFactory().getProductService().getById(pathId)?.vendor?.id == authUserId
}
