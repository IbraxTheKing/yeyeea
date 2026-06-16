
import ax.ibr.yeyeea.common.entities.Category
import ax.ibr.yeyeea.common.entities.Product
import ax.ibr.yeyeea.common.entities.User
import ax.ibr.yeyeea.business.implementations.BusinessFactory

/**
 * Peuplement de la base de données avec des données de test réalistes.
 * Lance ce fichier une seule fois pour initialiser la DB.
 */
fun main() {
    val factory = BusinessFactory()
    val categoryService = factory.getCategoryService()
    val productService  = factory.getProductService()
    val userService     = factory.getUserService()

    println("=== Début du peuplement ===\n")

    // ─────────────────────────────────────────────
    // CATÉGORIES PRINCIPALES
    // ─────────────────────────────────────────────

    val electronique = Category().apply {
        name          = "Électronique"
        description   = "Tous les appareils électroniques"
        isMainCategory = true
    }

    val informatique = Category().apply {
        name          = "Informatique"
        description   = "Matériel et accessoires informatiques"
        isMainCategory = true
    }

    val audioVideo = Category().apply {
        name          = "Audio & Vidéo"
        description   = "Son, image et multimédia"
        isMainCategory = true
    }

    val mobilier = Category().apply {
        name          = "Mobilier"
        description   = "Meubles et aménagement"
        isMainCategory = true
    }

    categoryService.add(electronique)
    categoryService.add(informatique)
    categoryService.add(audioVideo)
    categoryService.add(mobilier)
    println("✓ Catégories principales créées")

    // ─────────────────────────────────────────────
    // SOUS-CATÉGORIES : Électronique
    // ─────────────────────────────────────────────

    val smartphones = Category().apply {
        name           = "Smartphones"
        description    = "Téléphones intelligents"
        isMainCategory = false
        parentCategory = electronique
        // Tout smartphone a ces caractéristiques
        addDescription("Taille écran", "pouces")
        addDescription("RAM", "Go")
        addDescription("Stockage", "Go")
        addDescription("Batterie", "mAh")
        addDescription("Système", "Android / iOS")
        addDescription("5G", "Oui / Non")
    }

    val tablettes = Category().apply {
        name           = "Tablettes"
        description    = "Tablettes tactiles"
        isMainCategory = false
        parentCategory = electronique
        addDescription("Taille écran", "pouces")
        addDescription("RAM", "Go")
        addDescription("Stockage", "Go")
        addDescription("Batterie", "mAh")
        addDescription("Système", "Android / iPadOS / Windows")
        addDescription("Connectivité", "WiFi / WiFi + 4G")
    }

    val objetsConnectes = Category().apply {
        name           = "Objets connectés"
        description    = "Montres, bracelets, domotique"
        isMainCategory = false
        parentCategory = electronique
        addDescription("Type", "Montre / Bracelet / Capteur")
        addDescription("Compatibilité", "iOS / Android / Les deux")
        addDescription("Autonomie", "jours")
        addDescription("Étanchéité", "ATM")
    }

    categoryService.add(smartphones)
    categoryService.add(tablettes)
    categoryService.add(objetsConnectes)
    println("✓ Sous-catégories Électronique créées")

    // ─────────────────────────────────────────────
    // SOUS-CATÉGORIES : Informatique
    // ─────────────────────────────────────────────

    val pc = Category().apply {
        name           = "PC de bureau"
        description    = "Ordinateurs fixes"
        isMainCategory = false
        parentCategory = informatique
        // Tout PC a ces specs
        addDescription("Processeur", "ex: Intel Core i7-13700K")
        addDescription("RAM", "Go DDR5")
        addDescription("Stockage SSD", "Go / To")
        addDescription("Stockage HDD", "Go / To (optionnel)")
        addDescription("Carte graphique", "ex: RTX 4070")
        addDescription("Système d'exploitation", "Windows / Linux / Aucun")
        addDescription("Format", "Tour / Mini-ITX / All-in-One")
    }

    val laptops = Category().apply {
        name           = "Laptops"
        description    = "Ordinateurs portables"
        isMainCategory = false
        parentCategory = informatique
        addDescription("Processeur", "ex: Apple M3 / Intel i7")
        addDescription("RAM", "Go")
        addDescription("Stockage SSD", "Go / To")
        addDescription("Taille écran", "pouces")
        addDescription("Résolution", "ex: 1920x1080 / 2560x1600")
        addDescription("Batterie", "Wh")
        addDescription("Poids", "kg")
        addDescription("Carte graphique", "Intégrée / Dédiée")
    }

    val composants = Category().apply {
        name           = "Composants"
        description    = "Pièces détachées PC"
        isMainCategory = false
        parentCategory = informatique
        addDescription("Type de composant", "CPU / GPU / RAM / SSD / Carte mère / Alimentation")
        addDescription("Socket / Format", "ex: LGA1700 / AM5 / M.2 / PCIe 4.0")
        addDescription("Compatibilité", "ex: DDR5 / Intel 13e gen")
    }

    val peripheriques = Category().apply {
        name           = "Périphériques"
        description    = "Claviers, souris, écrans, etc."
        isMainCategory = false
        parentCategory = informatique
        addDescription("Type", "Clavier / Souris / Écran / Casque / Webcam")
        addDescription("Connexion", "USB / Bluetooth / Sans fil")
        addDescription("Rétroéclairage RGB", "Oui / Non")
    }

    categoryService.add(pc)
    categoryService.add(laptops)
    categoryService.add(composants)
    categoryService.add(peripheriques)
    println("✓ Sous-catégories Informatique créées")

    // ─────────────────────────────────────────────
    // SOUS-CATÉGORIES : Audio & Vidéo
    // ─────────────────────────────────────────────

    val casquesAudio = Category().apply {
        name           = "Casques audio"
        description    = "Casques et écouteurs"
        isMainCategory = false
        parentCategory = audioVideo
        addDescription("Type", "Intra-auriculaire / Supra-auriculaire / Circum-auriculaire")
        addDescription("Connexion", "Jack 3.5mm / USB-C / Bluetooth")
        addDescription("Réduction de bruit active", "Oui / Non")
        addDescription("Autonomie", "heures (si sans fil)")
        addDescription("Microphone intégré", "Oui / Non")
    }

    val ecrans = Category().apply {
        name           = "Écrans"
        description    = "Moniteurs et téléviseurs"
        isMainCategory = false
        parentCategory = audioVideo
        addDescription("Taille", "pouces")
        addDescription("Résolution", "ex: 1080p / 4K / 8K")
        addDescription("Dalle", "IPS / VA / OLED / AMOLED")
        addDescription("Taux de rafraîchissement", "Hz")
        addDescription("Temps de réponse", "ms")
        addDescription("Ports", "ex: HDMI 2.1 / DisplayPort 1.4 / USB-C")
        addDescription("HDR", "Oui / Non")
    }

    categoryService.add(casquesAudio)
    categoryService.add(ecrans)
    println("✓ Sous-catégories Audio & Vidéo créées")

    // ─────────────────────────────────────────────
    // SOUS-CATÉGORIES : Mobilier
    // ─────────────────────────────────────────────

    val chaisesGaming = Category().apply {
        name           = "Chaises gaming / bureau"
        description    = "Sièges ergonomiques"
        isMainCategory = false
        parentCategory = mobilier
        addDescription("Poids maximum supporté", "kg")
        addDescription("Hauteur réglable", "cm min - cm max")
        addDescription("Accoudoirs", "2D / 3D / 4D")
        addDescription("Matière", "Tissu / Simili cuir / Mesh")
        addDescription("Appuie-tête amovible", "Oui / Non")
        addDescription("Support lombaire", "Fixe / Réglable")
    }

    val bureaux = Category().apply {
        name           = "Bureaux"
        description    = "Tables de travail et gaming"
        isMainCategory = false
        parentCategory = mobilier
        addDescription("Dimensions", "L x P x H cm")
        addDescription("Hauteur réglable", "Oui / Non")
        addDescription("Poids maximum", "kg")
        addDescription("Matière plateau", "MDF / Bois / Verre")
        addDescription("Passage câbles intégré", "Oui / Non")
    }

    categoryService.add(chaisesGaming)
    categoryService.add(bureaux)
    println("✓ Sous-catégories Mobilier créées")

    // ─────────────────────────────────────────────
    // PRODUITS
    // ─────────────────────────────────────────────

    val products = listOf(

        // ── Smartphones ──
        Product().apply {
            name        = "iPhone 15 Pro Max"
            company     = "Apple"
            description = "Écran 6.7\", puce A17 Pro, 256 Go, USB-C, titane"
            price       = 1479.00f
            category    = smartphones
        },
        Product().apply {
            name        = "Samsung Galaxy S24 Ultra"
            company     = "Samsung"
            description = "Écran 6.8\" QHD+, Snapdragon 8 Gen 3, 12 Go RAM, 256 Go, S-Pen"
            price       = 1419.00f
            category    = smartphones
        },
        Product().apply {
            name        = "Google Pixel 8 Pro"
            company     = "Google"
            description = "Écran 6.7\" LTPO OLED, Tensor G3, 12 Go RAM, 128 Go, IA avancée"
            price       = 1099.00f
            category    = smartphones
        },

        // ── Laptops ──
        Product().apply {
            name        = "MacBook Pro 16\" M3 Max"
            company     = "Apple"
            description = "Puce M3 Max, 36 Go RAM unifiée, SSD 1 To, écran Liquid Retina XDR"
            price       = 4199.00f
            category    = laptops
        },
        Product().apply {
            name        = "Dell XPS 15"
            company     = "Dell"
            description = "Intel Core i9-13900H, 32 Go DDR5, SSD 1 To, RTX 4070, OLED 3.5K"
            price       = 2799.00f
            category    = laptops
        },
        Product().apply {
            name        = "ASUS ROG Zephyrus G14"
            company     = "ASUS"
            description = "Ryzen 9 7940HS, 32 Go DDR5, SSD 1 To, RX 7900S, écran 14\" 165Hz"
            price       = 1999.00f
            category    = laptops
        },

        // ── PC Bureau ──
        Product().apply {
            name        = "PC Gamer Yeyeea Pro"
            company     = "Yeyeea"
            description = "Intel i7-13700K, 32 Go DDR5, SSD 1 To NVMe, RTX 4070 Ti, W11"
            price       = 2299.00f
            category    = pc
        },
        Product().apply {
            name        = "Mac Mini M2 Pro"
            company     = "Apple"
            description = "Puce M2 Pro, 16 Go RAM, SSD 512 Go, compact et silencieux"
            price       = 1499.00f
            category    = pc
        },

        // ── Composants ──
        Product().apply {
            name        = "RTX 4090 Founders Edition"
            company     = "NVIDIA"
            description = "24 Go GDDR6X, PCIe 4.0, TDP 450W, la plus puissante du marché"
            price       = 1949.00f
            category    = composants
        },
        Product().apply {
            name        = "Samsung 990 Pro SSD 2 To"
            company     = "Samsung"
            description = "NVMe M.2 PCIe 4.0, lecture 7450 Mo/s, écriture 6900 Mo/s"
            price       = 189.99f
            category    = composants
        },
        Product().apply {
            name        = "Corsair Dominator DDR5 32 Go"
            company     = "Corsair"
            description = "2x16 Go, 6000MHz CL30, RGB, compatible Intel XMP 3.0"
            price       = 139.99f
            category    = composants
        },

        // ── Périphériques ──
        Product().apply {
            name        = "Logitech MX Master 3S"
            company     = "Logitech"
            description = "Souris sans fil, 8000 DPI, scroll MagSpeed, USB-C, silencieuse"
            price       = 109.99f
            category    = peripheriques
        },
        Product().apply {
            name        = "Keychron Q1 Pro"
            company     = "Keychron"
            description = "Clavier mécanique 75%, switches Gateron, RGB, Bluetooth + USB-C"
            price       = 199.99f
            category    = peripheriques
        },

        // ── Écrans ──
        Product().apply {
            name        = "LG UltraGear 27GP950"
            company     = "LG"
            description = "27\" 4K Nano IPS, 160Hz, G-Sync Compatible, HDR600, 1ms GtG"
            price       = 699.99f
            category    = ecrans
        },
        Product().apply {
            name        = "Samsung Odyssey OLED G8"
            company     = "Samsung"
            description = "34\" QD-OLED incurvé, 3440x1440, 175Hz, HDR True Black 400"
            price       = 1099.00f
            category    = ecrans
        },

        // ── Casques ──
        Product().apply {
            name        = "Sony WH-1000XM5"
            company     = "Sony"
            description = "Circum-auriculaire, ANC leader du marché, 30h autonomie, Bluetooth 5.2"
            price       = 299.99f
            category    = casquesAudio
        },
        Product().apply {
            name        = "Apple AirPods Pro 2"
            company     = "Apple"
            description = "Intra-auriculaire, ANC adaptatif, puce H2, USB-C, 6h autonomie"
            price       = 279.00f
            category    = casquesAudio
        },

        // ── Chaises ──
        Product().apply {
            name        = "Herman Miller Aeron"
            company     = "Herman Miller"
            description = "Mesh intégral, support lombaire PostureFit SL, accoudoirs 4D, taille B"
            price       = 1649.00f
            category    = chaisesGaming
        },
        Product().apply {
            name        = "Secretlab Titan Evo 2022"
            company     = "Secretlab"
            description = "Simili cuir SoftWeave, appuie-tête magnétique, lombaire intégré réglable"
            price       = 499.00f
            category    = chaisesGaming
        },

        // ── Bureaux ──
        Product().apply {
            name        = "Flexispot E7 Pro"
            company     = "Flexispot"
            description = "Bureau assis-debout électrique, hauteur 58-123cm, plateau 160x80cm, 125kg max"
            price       = 699.00f
            category    = bureaux
        },

        // ── Objets connectés ──
        Product().apply {
            name        = "Apple Watch Series 9"
            company     = "Apple"
            description = "45mm, Always-On LTPO OLED, puce S9, GPS+Cellular, WR50"
            price       = 499.00f
            category    = objetsConnectes
        },
        Product().apply {
            name        = "Garmin Fenix 7X Solar"
            company     = "Garmin"
            description = "Montre sport premium, GPS multibande, recharge solaire, 28 jours autonomie"
            price       = 899.99f
            category    = objetsConnectes
        },

        // ── Tablettes ──
        Product().apply {
            name        = "iPad Pro 12.9\" M2"
            company     = "Apple"
            description = "Puce M2, écran Liquid Retina XDR, 8 Go RAM, 256 Go, WiFi 6E"
            price       = 1219.00f
            category    = tablettes
        },
        Product().apply {
            name        = "Samsung Galaxy Tab S9 Ultra"
            company     = "Samsung"
            description = "14.6\" AMOLED 120Hz, Snapdragon 8 Gen 2, 12 Go RAM, 256 Go, S-Pen inclus"
            price       = 1199.00f
            category    = tablettes
        }
    )

    products.forEach { productService.add(it) }
    println("✓ ${products.size} produits créés")

    // ─────────────────────────────────────────────
    // UTILISATEURS
    // ─────────────────────────────────────────────

    val users = listOf(
        User("admin", "admin123", "admin@yeyeea.fr").apply {
            // type = UserType.ADMIN  // décommente si tu as UserType
        },
        User("ibrahim_b", "pass123", "ibrahim@yeyeea.fr"),
        User("alice_d", "pass456", "alice.dupont@gmail.com"),
        User("bob_m", "pass789", "bob.martin@outlook.com"),
        User("charlie_r", "pass000", "charlie.r@proton.me"),
        User("diana_k", "passabc", "diana.k@yeyeea.fr"),
    )

    users.forEach { userService.add(it) }
    println("✓ ${users.size} utilisateurs créés")

    // ─────────────────────────────────────────────
    // RÉSUMÉ
    // ─────────────────────────────────────────────

    println("""
        
=== Peuplement terminé ===
  Catégories principales : 4
  Sous-catégories        : ${categoryService.getAll().size - 4}
  Produits               : ${products.size}
  Utilisateurs           : ${users.size}
    """.trimIndent())
}
