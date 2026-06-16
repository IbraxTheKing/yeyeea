import ax.ibr.yeyeea.business.implementations.BusinessFactory
import ax.ibr.yeyeea.common.entities.Category
import ax.ibr.yeyeea.common.entities.Product
import ax.ibr.yeyeea.common.entities.User


object PopulateTest {


    @JvmStatic
    fun main(args: Array<String>) {


        val factory = BusinessFactory()

        val categorySrv = factory.getCategoryService()
        val productSrv = factory.getProductService()
        val userSrv = factory.getUserService()



        // =========================
        // CATEGORIES
        // =========================


        val informatique = Category().apply {

            name = "Informatique"
            description = "Matériel informatique"
            isMainCategory = true

            addDescription(
                "type",
                "Ordinateurs, composants et accessoires"
            )
        }


        categorySrv.add(informatique)



        val ordinateurs = Category().apply {

            name = "Ordinateurs"
            description = "PC fixes et portables"

            parentCategory = informatique

            addDescription(
                "usage",
                "Gaming / Bureautique"
            )
        }


        val composants = Category().apply {

            name = "Composants PC"
            description = "Pièces internes"

            parentCategory = informatique

            addDescription(
                "contient",
                "CPU GPU RAM SSD"
            )
        }



        val peripheriques = Category().apply {

            name = "Périphériques"
            description = "Accessoires PC"

            parentCategory = informatique
        }



        informatique.subCategories.add(ordinateurs)
        informatique.subCategories.add(composants)
        informatique.subCategories.add(peripheriques)


        categorySrv.add(ordinateurs)
        categorySrv.add(composants)
        categorySrv.add(peripheriques)



        // =========================
        // PRODUITS
        // =========================


        val cpu = Product().apply {

            name = "Ryzen 7 7800X3D"
            description = "CPU gaming AMD"
            company = "AMD"
            price = 399.99f

            category = composants
        }



        val gpu = Product().apply {

            name = "RTX 4070 Super"
            description = "Carte graphique NVIDIA"
            company = "NVIDIA"
            price = 649.99f

            category = composants
        }



        val ram = Product().apply {

            name = "Kingston Fury DDR5 32Go"
            description = "Mémoire vive DDR5"
            company = "Kingston"
            price = 120f

            category = composants
        }



        val pcGaming = Product().apply {

            name = "ASUS ROG Gaming"
            description = "PC portable gamer"
            company = "ASUS"
            price = 1599.99f

            category = ordinateurs
        }



        val clavier = Product().apply {

            name = "Logitech G915"
            description = "Clavier mécanique sans fil"
            company = "Logitech"
            price = 199.99f

            category = peripheriques
        }



        productSrv.add(cpu)
        productSrv.add(gpu)
        productSrv.add(ram)
        productSrv.add(pcGaming)
        productSrv.add(clavier)




        // =========================
        // USERS
        // =========================


        val admin = User().apply {

            username = "admin"
            password = "admin123"
            email = "admin@shop.com"

            // si ton setter existe
            // type = UserType.ADMIN
        }


        val client = User().apply {

            username = "client"
            password = "client123"
            email = "client@shop.com"
        }



        userSrv.add(admin)
        userSrv.add(client)



        println(
            """
            
            =============================
             DATABASE POPULATED SUCCESS
            =============================
            
            Categories:
             - Informatique
                - Ordinateurs
                - Composants PC
                - Périphériques
                
            Products:
             - Ryzen 7 7800X3D
             - RTX 4070 Super
             - Kingston DDR5
             - ASUS ROG
             - Logitech G915
             
            Users:
             - admin
             - client
            
            =============================
            """.trimIndent()
        )
    }
}