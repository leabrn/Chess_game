import java.util.Scanner;

public class Menu {
    public static void menu() {

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        //initialisation des couleurs
        String blanc = "\u001B[47m";
        String noir = "\u001B[40m";
        String texteNoir = "\u001B[30m";
        String texteBlanc = "\u001B[37m";
        String caseBlanche = blanc + texteNoir;
        String caseNoire = noir + texteBlanc;


        //initialisation du plateau
        String[][] plateauAffichage = {
                {caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire},
                {caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche},
                {caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire},
                {caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche},
                {caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire},
                {caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche},
                {caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire},
                {caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche, caseNoire, caseBlanche}
        };


        //création tableau stockage -> majuscule = noir, miniscule = blanc

        char[][] plateauStockage = {
                {'T', 'C', 'F', 'D', 'R', 'F', 'C', 'T'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'t', 'c', 'f', 'd', 'r', 'f', 'c', 't'}

        };


        //Iniatialisation du nombre de tour joué
        int tourJoue = 1;

        //Initialisation des déplacements pour le roque
        boolean PasDeplacementRoiBlanc = true;
        boolean PasDeplacementTourBlancheGauche = true;
        boolean PasDeplacementTourBlancheDroite = true;
        boolean PasDeplacementRoiNoir = true;
        boolean PasDeplacementTourNoireGauche = true;
        boolean PasDeplacementTourNoireDroite = true;

        String reponse; //initialisation de la reponse de l'utilisateur
        int ligneUtilisateur = -1, colonneUtilisateur = -1, ligneJoue = -1, colonneJoue = -1; //initialisation des différentes lignes et colonnes
        int derniereLigneUtilisateurBlanc = -1, derniereColonneutilisateurBlanc = -1, derniereLigneUtilisateurNoir = -1, derniereColonneutilisateurNoir = -1; //initialisation des dernières colonne et ligne utilisateur (ici à -1 car inexistante au 1er tour)
        double scoreBot;//permettra de calculer le coup qui rapporte le plus de points
        char[][] plateauFictif= new char[plateauStockage.length][plateauStockage[0].length]; //création d'un plateau fictif
        boolean GameMode;//savoir si l'on joue contre un bot ou en 1v1
        boolean abandonBlanc = false;
        boolean abandonNoir = false;
        int compteurRepetitionsBlanc = 0, compteurRepetitionsNoir = 0;

        do {
            System.out.print("Souhaitez-vous jouer en 1v1(1) ou contre un bot(0) ? ");
            reponse = scanner.nextLine();
        }while (!reponse.equals("0")
                && !reponse.equals("1"));

        //Choix du jeu en 1v1
        if (reponse.equals("1")) {
            GameMode = false;
            do {
                //si aux blancs de jouer si tour impair
                if (tourJoue % 2 != 0) {

                    System.out.println();


                    affichagePlateauBlanc(plateauStockage, plateauAffichage);
                    System.out.println("\nAux blancs de jouer !");
                    System.out.println("\nQuelle case voulez-vous bouger ? (tapez 0 si vous souhaitez abandonner)\n");

                    reponse = scanner.nextLine();

                    if (!reponse.equals("0")) {
                        //verifier la reponse est bien une coordonnée
                        if (reponse.length() != 2) {

                            while (reponse.length() != 2) {
                                System.out.println("\nLa case est fausse. Quelle case voulez-vous bouger ?\n");

                                reponse = scanner.nextLine();
                            }
                        }

                        colonneUtilisateur = Deplacement.trouvercolonne(reponse);
                        ligneUtilisateur = Deplacement.trouverligne(reponse);

                        while (Deplacement.nbPossibilités(plateauStockage, ligneUtilisateur, colonneUtilisateur, tourJoue, derniereLigneUtilisateurNoir, derniereColonneutilisateurNoir, PasDeplacementTourBlancheGauche, PasDeplacementRoiBlanc, PasDeplacementTourBlancheDroite) == 0
                                || colonneUtilisateur == -1
                                || ligneUtilisateur == -1
                                || reponse.length() != 2
                                || !Deplacement.bonneCouleur(tourJoue, ligneUtilisateur, colonneUtilisateur, plateauStockage)) {
                            System.out.println("\nLa case est fausse. Quelle case voulez-vous bouger ?\n");

                            reponse = scanner.nextLine();
                            colonneUtilisateur = Deplacement.trouvercolonne(reponse);
                            ligneUtilisateur = Deplacement.trouverligne(reponse);
                        }

                        System.out.println("\nOù voulez-vous vous déplacer ?\n");

                        reponse = scanner.nextLine();

                        if (reponse.length() != 2) {

                            while (reponse.length() != 2) {
                                System.out.println("\nLa case est fausse. Où voulez-vous vous déplacer ?\n");

                                reponse = scanner.nextLine();
                            }
                        }

                        colonneJoue = Deplacement.trouvercolonne(reponse);
                        ligneJoue = Deplacement.trouverligne(reponse);

                        while (colonneJoue == -1
                                || ligneJoue == -1
                                || reponse.length() != 2) {
                            System.out.println("\nLa case est fausse. Où voulez-vous vous déplacer ?\n");

                            reponse = scanner.nextLine();
                            colonneJoue = Deplacement.trouvercolonne(reponse);
                            ligneJoue = Deplacement.trouverligne(reponse);
                        }

                        while (!Deplacement.Possibilités(plateauStockage[ligneUtilisateur][colonneUtilisateur], ligneUtilisateur, colonneUtilisateur, ligneJoue, colonneJoue, tourJoue, plateauStockage, derniereLigneUtilisateurNoir, derniereColonneutilisateurNoir, PasDeplacementTourBlancheGauche, PasDeplacementRoiBlanc, PasDeplacementTourBlancheDroite, GameMode)) {
                            System.out.println("\nVous ne pouvez pas aller ici.");

                            reponse = caseJoue();
                            colonneJoue = Deplacement.trouvercolonne(reponse);
                            ligneJoue = Deplacement.trouverligne(reponse);
                        }

                        Deplacement.deplacementPiece(plateauStockage, ligneUtilisateur, colonneUtilisateur, ligneJoue, colonneJoue);

                        affichagePlateauBlanc(plateauStockage, plateauAffichage);

                        if (derniereColonneutilisateurBlanc == colonneJoue && derniereLigneUtilisateurBlanc == ligneJoue)
                            compteurRepetitionsBlanc ++;
                        else
                            compteurRepetitionsBlanc = 0;

                        if (compteurRepetitionsBlanc >= 5){
                            System.out.println("Vous avez fait trois fois ou plus le même mouvement, souhaitez-vous déclarer forfait ? (tapez 0 si vous souhaitez abandonner et n'importe quoi sinon)");
                            reponse = scanner.nextLine();
                            if (reponse.equals("0")) {
                                abandonBlanc = true;
                                abandonNoir = true;
                            }
                        }

                        derniereLigneUtilisateurBlanc = ligneUtilisateur;
                        derniereColonneutilisateurBlanc = colonneUtilisateur;

                        if (ligneUtilisateur == 7 && colonneUtilisateur == 0)
                            PasDeplacementTourBlancheGauche = false;
                        else if (ligneUtilisateur == 7 && colonneUtilisateur == 7)
                            PasDeplacementTourBlancheDroite = false;
                        else if (ligneUtilisateur == 7 && colonneUtilisateur == 4)
                            PasDeplacementRoiBlanc = false;
                    }
                    else {
                        abandonBlanc = true;
                        System.out.println("\nLes blancs ont abandonné. Est-ce que les noirs veulent aussi ? (tapez 0 si vous souhaitez abandonner et n'importe quoi sinon)");
                        reponse = scanner.nextLine();
                        if (reponse.equals("0"))
                            abandonNoir = true;
                    }


                }

                //si aux noirs de jouer
                else {
                    System.out.println();

                    affichagePlateauNoir(plateauStockage, plateauAffichage);
                    System.out.println("\nAux noirs de jouer !");
                    System.out.println("\nQuelle case voulez-vous bouger ? (tapez 0 si vous souhaitez abandonner)\n");

                    reponse = scanner.nextLine();

                    if (!reponse.equals("0")) {

                        //verifier la reponse est bien une coordonnée
                        if (reponse.length() != 2) {

                            while (reponse.length() != 2) {
                                System.out.println("\nLa case est fausse. Quelle case voulez-vous bouger ?\n");

                                reponse = scanner.nextLine();
                            }
                        }

                        colonneUtilisateur = Deplacement.trouvercolonne(reponse);
                        ligneUtilisateur = Deplacement.trouverligne(reponse);

                        while (Deplacement.nbPossibilités(plateauStockage, ligneUtilisateur, colonneUtilisateur, tourJoue, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite) == 0
                                || colonneUtilisateur == -1 || ligneUtilisateur == -1
                                || reponse.length() != 2
                                || !Deplacement.bonneCouleur(tourJoue, ligneUtilisateur, colonneUtilisateur, plateauStockage)) {
                            System.out.println("\nLa case est fausse. Quelle case voulez-vous bouger ?\n");

                            reponse = scanner.nextLine();
                            colonneUtilisateur = Deplacement.trouvercolonne(reponse);
                            ligneUtilisateur = Deplacement.trouverligne(reponse);
                        }


                        System.out.println("\nOù voulez-vous vous déplacer ?\n");

                        reponse = scanner.nextLine();

                        if (reponse.length() != 2) {

                            while (reponse.length() != 2) {
                                System.out.println("\nLa case est fausse. Où voulez-vous vous déplacer ?\n");

                                reponse = scanner.nextLine();
                            }
                        }

                        colonneJoue = Deplacement.trouvercolonne(reponse);
                        ligneJoue = Deplacement.trouverligne(reponse);

                        while (colonneJoue == -1 || ligneJoue == -1 || reponse.length() != 2) {
                            System.out.println("\nLa case est fausse. Où voulez-vous vous déplacer ?\n");

                            reponse = scanner.nextLine();
                            colonneJoue = Deplacement.trouvercolonne(reponse);
                            ligneJoue = Deplacement.trouverligne(reponse);
                        }

                        while (!Deplacement.Possibilités(plateauStockage[ligneUtilisateur][colonneUtilisateur], ligneUtilisateur, colonneUtilisateur, ligneJoue, colonneJoue, tourJoue, plateauStockage, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite, GameMode)) {

                            System.out.println("\nVous ne pouvez pas aller ici.");

                            reponse = caseJoue();
                            colonneJoue = Deplacement.trouvercolonne(reponse);
                            ligneJoue = Deplacement.trouverligne(reponse);
                        }


                        Deplacement.deplacementPiece(plateauStockage, ligneUtilisateur, colonneUtilisateur, ligneJoue, colonneJoue);

                        affichagePlateauNoir(plateauStockage, plateauAffichage);

                        if (derniereColonneutilisateurNoir == colonneJoue && derniereLigneUtilisateurNoir == ligneJoue)
                            compteurRepetitionsNoir ++;
                        else
                            compteurRepetitionsNoir = 0;

                        if (compteurRepetitionsNoir >= 5){
                            System.out.println("Vous avez fait trois fois ou plus le même mouvement, souhaitez-vous déclarer forfait ? (tapez 0 si vous souhaitez abandonner et n'importe quoi sinon)");
                            reponse = scanner.nextLine();
                            if (reponse.equals("0")) {
                                abandonNoir = true;
                                abandonBlanc = true;
                            }
                        }

                        derniereLigneUtilisateurNoir = ligneUtilisateur;
                        derniereColonneutilisateurNoir = colonneUtilisateur;

                        if (ligneUtilisateur == 0 && colonneUtilisateur == 0)
                            PasDeplacementTourNoireGauche = false;
                        else if (ligneUtilisateur == 0 && colonneUtilisateur == 7)
                            PasDeplacementTourNoireDroite = false;
                        else if (ligneUtilisateur == 0 && colonneUtilisateur == 4)
                            PasDeplacementRoiNoir = false;
                    }
                    else {
                        abandonNoir = true;
                        System.out.println("\nLes noirs ont abandonné. Est-ce que les blancs veulent aussi ? (tapez 0 si vous souhaitez abandonner et n'importe quoi sinon)");
                        reponse = scanner.nextLine();
                        if (reponse.equals("0"))
                            abandonBlanc = true;
                    }

                }
                tourJoue++;

            } while (!Deplacement.echecetmatblanc(tourJoue, plateauStockage, derniereLigneUtilisateurNoir, derniereColonneutilisateurNoir, PasDeplacementTourBlancheGauche, PasDeplacementRoiBlanc, PasDeplacementTourBlancheDroite)
                    && !Deplacement.echecetmatnoir(tourJoue, plateauStockage, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite)
                    && !Deplacement.positionMorte(plateauStockage)
                    && !Deplacement.pat(plateauStockage, tourJoue, derniereLigneUtilisateurNoir, derniereColonneutilisateurNoir, PasDeplacementTourBlancheGauche, PasDeplacementRoiBlanc, PasDeplacementTourBlancheDroite)
                    && !Deplacement.pat(plateauStockage, tourJoue, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite)
                    && !abandonBlanc
                    && !abandonNoir);
        }

        //choix du jeu contre un bot
        else{
            GameMode = true;

            do {
                System.out.print("\nSouhaitez-vous jouer en mode facile(0) ou normal(1) ? ");
                reponse = scanner.nextLine();
            }while(!reponse.equals("0") && !reponse.equals("1"));

            //si jeu en mode facile
            if (reponse.equals("0")){
                do {
                    //si aux blancs de jouer si tour impair
                    if (tourJoue % 2 != 0) {

                        System.out.println();


                        affichagePlateauBlanc(plateauStockage, plateauAffichage);
                        System.out.println("\nA vous de jouer !");
                        System.out.println("\nQuelle case voulez-vous bouger ? (tapez 0 si vous souhaitez abandonner)\n");

                        reponse = scanner.nextLine();

                        if (!reponse.equals("0")) {

                            //verifier la reponse est bien une coordonnée
                            if (reponse.length() != 2) {

                                while (reponse.length() != 2) {
                                    System.out.println("\nLa case est fausse. Quelle case voulez-vous bouger ?\n");

                                    reponse = scanner.nextLine();
                                }
                            }

                            colonneUtilisateur = Deplacement.trouvercolonne(reponse);
                            ligneUtilisateur = Deplacement.trouverligne(reponse);

                            while (Deplacement.nbPossibilités(plateauStockage, ligneUtilisateur, colonneUtilisateur, tourJoue, derniereLigneUtilisateurNoir, derniereColonneutilisateurNoir, PasDeplacementTourBlancheGauche, PasDeplacementRoiBlanc, PasDeplacementTourBlancheDroite) == 0
                                    || colonneUtilisateur == -1
                                    || ligneUtilisateur == -1
                                    || reponse.length() != 2
                                    || !Deplacement.bonneCouleur(tourJoue, ligneUtilisateur, colonneUtilisateur, plateauStockage)) {
                                System.out.println("\nLa case est fausse. Quelle case voulez-vous bouger ?\n");

                                reponse = scanner.nextLine();
                                colonneUtilisateur = Deplacement.trouvercolonne(reponse);
                                ligneUtilisateur = Deplacement.trouverligne(reponse);
                            }

                            System.out.println("\nOù voulez-vous vous déplacer ?\n");

                            reponse = scanner.nextLine();

                            if (reponse.length() != 2) {

                                while (reponse.length() != 2) {
                                    System.out.println("\nLa case est fausse. Où voulez-vous vous déplacer ?\n");

                                    reponse = scanner.nextLine();
                                }
                            }

                            colonneJoue = Deplacement.trouvercolonne(reponse);
                            ligneJoue = Deplacement.trouverligne(reponse);

                            while (colonneJoue == -1
                                    || ligneJoue == -1
                                    || reponse.length() != 2) {
                                System.out.println("\nLa case est fausse. Où voulez-vous vous déplacer ?\n");

                                reponse = scanner.nextLine();
                                colonneJoue = Deplacement.trouvercolonne(reponse);
                                ligneJoue = Deplacement.trouverligne(reponse);
                            }

                            while (!Deplacement.Possibilités(plateauStockage[ligneUtilisateur][colonneUtilisateur], ligneUtilisateur, colonneUtilisateur, ligneJoue, colonneJoue, tourJoue, plateauStockage, derniereLigneUtilisateurNoir, derniereColonneutilisateurNoir, PasDeplacementTourBlancheGauche, PasDeplacementRoiBlanc, PasDeplacementTourBlancheDroite, GameMode)) {
                                System.out.println("\nVous ne pouvez pas aller ici.");

                                reponse = caseJoue();
                                colonneJoue = Deplacement.trouvercolonne(reponse);
                                ligneJoue = Deplacement.trouverligne(reponse);
                            }

                            Deplacement.deplacementPiece(plateauStockage, ligneUtilisateur, colonneUtilisateur, ligneJoue, colonneJoue);

                            affichagePlateauBlanc(plateauStockage, plateauAffichage);

                            if (derniereColonneutilisateurBlanc == colonneJoue && derniereLigneUtilisateurBlanc == ligneJoue)
                                compteurRepetitionsBlanc ++;
                            else
                                compteurRepetitionsBlanc = 0;

                            if (compteurRepetitionsBlanc >= 5){
                                System.out.println("Vous avez fait trois fois ou plus le même mouvement, souhaitez-vous déclarer forfait ? (tapez 0 si vous souhaitez abandonner et n'importe quoi sinon)");
                                reponse = scanner.nextLine();
                                if (reponse.equals("0")) {
                                    abandonBlanc = true;
                                    abandonNoir = true;
                                }
                            }

                            derniereLigneUtilisateurBlanc = ligneUtilisateur;
                            derniereColonneutilisateurBlanc = colonneUtilisateur;

                            if (ligneUtilisateur == 7 && colonneUtilisateur == 0)
                                PasDeplacementTourBlancheGauche = false;
                            else if (ligneUtilisateur == 7 && colonneUtilisateur == 7)
                                PasDeplacementTourBlancheDroite = false;
                            else if (ligneUtilisateur == 7 && colonneUtilisateur == 4)
                                PasDeplacementRoiBlanc = false;

                        }
                        else {
                            abandonBlanc = true;
                        }
                    }

                    //si aux noirs de jouer (bot)
                    else {
                        System.out.println();
                        System.out.println("Le bot a joué.");
                        do{
                            ligneUtilisateur = (int)(Math.random()*8);
                            colonneUtilisateur = (int)(Math.random()*8);
                        }while (Deplacement.nbPossibilités(plateauStockage, ligneUtilisateur, colonneUtilisateur, tourJoue, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite) == 0
                                || !Deplacement.bonneCouleur(tourJoue, ligneUtilisateur, colonneUtilisateur, plateauStockage));

                        do {
                            ligneJoue = (int)(Math.random()*8);
                            colonneJoue = (int)(Math.random()*8);
                        }while (!Deplacement.Possibilités(plateauStockage[ligneUtilisateur][colonneUtilisateur], ligneUtilisateur, colonneUtilisateur, ligneJoue, colonneJoue, tourJoue, plateauStockage, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite, GameMode));



                        Deplacement.deplacementPiece(plateauStockage, ligneUtilisateur, colonneUtilisateur, ligneJoue, colonneJoue);

                        if (derniereColonneutilisateurNoir == colonneJoue && derniereLigneUtilisateurNoir == ligneJoue)
                            compteurRepetitionsNoir ++;
                        else
                            compteurRepetitionsNoir = 0;

                        if (compteurRepetitionsNoir >= 5) {
                            System.out.println("Le bot a fait trois fois le même mouvement !");
                            abandonNoir = true;
                            abandonBlanc = true;
                        }



                        derniereLigneUtilisateurNoir = ligneUtilisateur;
                        derniereColonneutilisateurNoir = colonneUtilisateur;

                        if (ligneUtilisateur == 0 && colonneUtilisateur == 0)
                            PasDeplacementTourNoireGauche = false;
                        else if (ligneUtilisateur == 0 && colonneUtilisateur == 7)
                            PasDeplacementTourNoireDroite = false;
                        else if (ligneUtilisateur == 0 && colonneUtilisateur == 4)
                            PasDeplacementRoiNoir = false;

                    }
                    tourJoue++;

                } while (!Deplacement.echecetmatblanc(tourJoue, plateauStockage, derniereLigneUtilisateurNoir, derniereColonneutilisateurNoir, PasDeplacementTourBlancheGauche, PasDeplacementRoiBlanc, PasDeplacementTourBlancheDroite)
                        && !Deplacement.echecetmatnoir(tourJoue, plateauStockage, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite)
                        && !Deplacement.positionMorte(plateauStockage)
                        && !Deplacement.pat(plateauStockage, tourJoue, derniereLigneUtilisateurNoir, derniereColonneutilisateurNoir, PasDeplacementTourBlancheGauche, PasDeplacementRoiBlanc, PasDeplacementTourBlancheDroite)
                        && !Deplacement.pat(plateauStockage, tourJoue, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite)
                        && !abandonBlanc
                        && !abandonNoir);
            }

            //si le joueur choisit le mode normal
            else {
                do {
                    //si aux blancs de jouer si tour impair
                    if (tourJoue % 2 != 0) {

                        System.out.println();


                        affichagePlateauBlanc(plateauStockage, plateauAffichage);
                        System.out.println("\nA vous de jouer !");
                        System.out.println("\nQuelle case voulez-vous bouger ? (tapez 0 si vous souhaitez abandonner)\n");

                        reponse = scanner.nextLine();

                        if (!reponse.equals("0")) {

                            //verifier la reponse est bien une coordonnée
                            if (reponse.length() != 2) {

                                while (reponse.length() != 2) {
                                    System.out.println("\nLa case est fausse. Quelle case voulez-vous bouger ?\n");

                                    reponse = scanner.nextLine();
                                }
                            }

                            colonneUtilisateur = Deplacement.trouvercolonne(reponse);
                            ligneUtilisateur = Deplacement.trouverligne(reponse);

                            while (Deplacement.nbPossibilités(plateauStockage, ligneUtilisateur, colonneUtilisateur, tourJoue, derniereLigneUtilisateurNoir, derniereColonneutilisateurNoir, PasDeplacementTourBlancheGauche, PasDeplacementRoiBlanc, PasDeplacementTourBlancheDroite) == 0
                                    || colonneUtilisateur == -1
                                    || ligneUtilisateur == -1
                                    || reponse.length() != 2
                                    || !Deplacement.bonneCouleur(tourJoue, ligneUtilisateur, colonneUtilisateur, plateauStockage)) {
                                System.out.println("\nLa case est fausse. Quelle case voulez-vous bouger ?\n");

                                reponse = scanner.nextLine();
                                colonneUtilisateur = Deplacement.trouvercolonne(reponse);
                                ligneUtilisateur = Deplacement.trouverligne(reponse);
                            }

                            System.out.println("\nOù voulez-vous vous déplacer ?\n");

                            reponse = scanner.nextLine();

                            if (reponse.length() != 2) {

                                while (reponse.length() != 2) {
                                    System.out.println("\nLa case est fausse. Où voulez-vous vous déplacer ?\n");

                                    reponse = scanner.nextLine();
                                }
                            }

                            colonneJoue = Deplacement.trouvercolonne(reponse);
                            ligneJoue = Deplacement.trouverligne(reponse);

                            while (colonneJoue == -1
                                    || ligneJoue == -1
                                    || reponse.length() != 2) {
                                System.out.println("\nLa case est fausse. Où voulez-vous vous déplacer ?\n");

                                reponse = scanner.nextLine();
                                colonneJoue = Deplacement.trouvercolonne(reponse);
                                ligneJoue = Deplacement.trouverligne(reponse);
                            }

                            while (!Deplacement.Possibilités(plateauStockage[ligneUtilisateur][colonneUtilisateur], ligneUtilisateur, colonneUtilisateur, ligneJoue, colonneJoue, tourJoue, plateauStockage, derniereLigneUtilisateurNoir, derniereColonneutilisateurNoir, PasDeplacementTourBlancheGauche, PasDeplacementRoiBlanc, PasDeplacementTourBlancheDroite, GameMode)) {
                                System.out.println("\nVous ne pouvez pas aller ici.");

                                reponse = caseJoue();
                                colonneJoue = Deplacement.trouvercolonne(reponse);
                                ligneJoue = Deplacement.trouverligne(reponse);
                            }

                            Deplacement.deplacementPiece(plateauStockage, ligneUtilisateur, colonneUtilisateur, ligneJoue, colonneJoue);

                            affichagePlateauBlanc(plateauStockage, plateauAffichage);

                            if (derniereColonneutilisateurBlanc == colonneJoue && derniereLigneUtilisateurBlanc == ligneJoue)
                                compteurRepetitionsBlanc ++;
                            else
                                compteurRepetitionsBlanc = 0;

                            if (compteurRepetitionsBlanc >= 5){
                                System.out.println("Vous avez fait trois fois ou plus le même mouvement, souhaitez-vous déclarer forfait ? (tapez 0 si vous souhaitez abandonner et n'importe quoi sinon)");
                                reponse = scanner.nextLine();
                                if (reponse.equals("0")) {
                                    abandonBlanc = true;
                                    abandonNoir = true;
                                }
                            }

                            derniereLigneUtilisateurBlanc = ligneUtilisateur;
                            derniereColonneutilisateurBlanc = colonneUtilisateur;

                            if (ligneUtilisateur == 7 && colonneUtilisateur == 0)
                                PasDeplacementTourBlancheGauche = false;
                            else if (ligneUtilisateur == 7 && colonneUtilisateur == 7)
                                PasDeplacementTourBlancheDroite = false;
                            else if (ligneUtilisateur == 7 && colonneUtilisateur == 4)
                                PasDeplacementRoiBlanc = false;

                        }
                        else {
                            abandonBlanc = true;
                        }
                    }

                    //si aux noirs de jouer (bot)
                    else {
                        System.out.println();
                        System.out.println("Le bot a joué.");
                        //score que le bot ne peut pas atteindre
                        scoreBot = -100000000;

                        //boucle pour trouver une pièce jouable
                        for (int ligne = 0; ligne < plateauStockage.length; ligne++) {
                            for (int colonne = 0; colonne < plateauStockage[ligne].length; colonne++) {
                                if (Deplacement.nbPossibilités(plateauStockage, ligne, colonne, tourJoue, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite) > 0
                                        && (plateauStockage[ligne][colonne] == 'C'
                                        || plateauStockage[ligne][colonne] == 'D'
                                        || plateauStockage[ligne][colonne] == 'P'
                                        || plateauStockage[ligne][colonne] == 'R'
                                        || plateauStockage[ligne][colonne] == 'F'
                                        || plateauStockage[ligne][colonne] == 'T'))

                                    for (int ligne2 = 0; ligne2 < plateauStockage.length; ligne2++) {
                                        for (int colonne2 = 0; colonne2 < plateauStockage[ligne2].length; colonne2++) {
                                            if (Deplacement.PossibilitésSansDeplacement(plateauStockage[ligne][colonne], ligne, colonne, ligne2, colonne2, tourJoue, plateauStockage, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite)) {

                                                //Création d'un plateau fictif qui permettra de calculer le score
                                                for (int ligne3 = 0; ligne3 < plateauStockage.length; ligne3++) {
                                                    for (int colonne3 = 0; colonne3 < plateauStockage[ligne3].length; colonne3++) {
                                                        plateauFictif[ligne3][colonne3] = plateauStockage[ligne3][colonne3];
                                                    }
                                                }

                                                if (Deplacement.Possibilités(plateauFictif[ligne][colonne], ligne, colonne, ligne2, colonne2, tourJoue, plateauFictif, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite, GameMode)) {
                                                    Deplacement.deplacementPiece(plateauFictif, ligne, colonne, ligne2, colonne2);

                                                    if (Deplacement.scorePlateau(plateauFictif, ligne2, colonne2) > scoreBot) {
                                                        scoreBot = Deplacement.scorePlateau(plateauFictif, ligne2, colonne2);
                                                        ligneUtilisateur = ligne;
                                                        colonneUtilisateur = colonne;
                                                        ligneJoue = ligne2;
                                                        colonneJoue = colonne2;
                                                    }
                                                }
                                            }
                                        }
                                    }
                            }
                        }

                        Deplacement.Possibilités(plateauStockage[ligneUtilisateur][colonneUtilisateur], ligneUtilisateur, colonneUtilisateur, ligneJoue, colonneJoue, tourJoue, plateauStockage, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite, GameMode);
                        Deplacement.deplacementPiece(plateauStockage, ligneUtilisateur, colonneUtilisateur, ligneJoue, colonneJoue);

                        if (derniereColonneutilisateurNoir == colonneJoue && derniereLigneUtilisateurNoir == ligneJoue)
                            compteurRepetitionsNoir ++;
                        else
                            compteurRepetitionsNoir = 0;

                        if (compteurRepetitionsNoir >= 5){
                            System.out.println("Le bot a fait trois fois le même mouvement !");
                            abandonNoir = true;
                            abandonBlanc = true;
                        }

                        derniereLigneUtilisateurNoir = ligneUtilisateur;
                        derniereColonneutilisateurNoir = colonneUtilisateur;

                        if (ligneUtilisateur == 0 && colonneUtilisateur == 0)
                            PasDeplacementTourNoireGauche = false;
                        else if (ligneUtilisateur == 0 && colonneUtilisateur == 7)
                            PasDeplacementTourNoireDroite = false;
                        else if (ligneUtilisateur == 0 && colonneUtilisateur == 4)
                            PasDeplacementRoiNoir = false;
                    }
                    tourJoue++;

                } while (!Deplacement.echecetmatblanc(tourJoue, plateauStockage, derniereLigneUtilisateurNoir, derniereColonneutilisateurNoir, PasDeplacementTourBlancheGauche, PasDeplacementRoiBlanc, PasDeplacementTourBlancheDroite)
                        && !Deplacement.echecetmatnoir(tourJoue, plateauStockage, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite)
                        && !Deplacement.positionMorte(plateauStockage)
                        && !Deplacement.pat(plateauStockage, tourJoue, derniereLigneUtilisateurNoir, derniereColonneutilisateurNoir, PasDeplacementTourBlancheGauche, PasDeplacementRoiBlanc, PasDeplacementTourBlancheDroite)
                        && !Deplacement.pat(plateauStockage, tourJoue, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite)
                        && !abandonBlanc
                        && !abandonNoir);

            }
        }

        if (Deplacement.positionMorte(plateauStockage)
                || Deplacement.pat(plateauStockage, tourJoue, derniereLigneUtilisateurNoir, derniereColonneutilisateurNoir, PasDeplacementTourBlancheGauche, PasDeplacementRoiBlanc, PasDeplacementTourBlancheDroite)
                || Deplacement.pat(plateauStockage, tourJoue, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite)
                ||( abandonBlanc
                && abandonNoir))
            System.out.println("\négalité !");

        else if (Deplacement.echecetmatblanc(tourJoue, plateauStockage, derniereLigneUtilisateurNoir, derniereColonneutilisateurNoir, PasDeplacementTourBlancheGauche, PasDeplacementRoiBlanc, PasDeplacementTourBlancheDroite)
                || abandonBlanc)
            System.out.println("\nVictoire des noirs !");

        else if (Deplacement.echecetmatnoir(tourJoue, plateauStockage, derniereLigneUtilisateurBlanc, derniereColonneutilisateurBlanc, PasDeplacementTourNoireGauche, PasDeplacementRoiNoir, PasDeplacementTourNoireDroite)
                || abandonNoir)
            System.out.println("\nVictoire des blancs !");



        scanner.close();
    }

    //affichage plateau côté blanc
    public static void affichagePlateauBlanc(char[][] plateauStockage, String[][] plateauAffichage) {
        //les pièces -> majuscule = noir, miniscule = blanc
        String TOUR = "\u265C";
        String tour = "\u2656";
        String ROI = "\u265A";
        String roi = "\u2654";
        String DAME = "\u265B";
        String dame = "\u2655";
        String FOU = "\u265D";
        String fou = "\u2657";
        String CAVALIER = "\u265E";
        String cavalier = "\u2658";
        String PION = "\u265F";
        String pion = "\u2659";


        //couleur vide
        String vide = "\u001B[0m";

        System.out.println("   " + " A  " + " B  " + " C  " + " D  " + " E  " + " F  " + " G  " + " H  ");
        for (int ligne = 0; ligne < plateauAffichage.length; ligne++) {
            for (int colonne = 0; colonne < plateauAffichage[ligne].length; colonne++) {
                if (colonne == 0) {
                    System.out.print(" " + (plateauAffichage.length - ligne) + " ");
                }
                System.out.print(plateauAffichage[ligne][colonne] + " ");
                switch (plateauStockage[ligne][colonne]) {
                    case 'T':
                        System.out.print(TOUR);
                        break;
                    case 't':
                        System.out.print(tour);
                        break;
                    case 'R':
                        System.out.print(ROI);
                        break;
                    case 'r':
                        System.out.print(roi);
                        break;
                    case 'D':
                        System.out.print(DAME);
                        break;
                    case 'd':
                        System.out.print(dame);
                        break;
                    case 'F':
                        System.out.print(FOU);
                        break;
                    case 'f':
                        System.out.print(fou);
                        break;
                    case 'C':
                        System.out.print(CAVALIER);
                        break;
                    case 'c':
                        System.out.print(cavalier);
                        break;
                    case 'P':
                        System.out.print(PION);
                        break;
                    case 'p':
                        System.out.print(pion);
                        break;
                    default:
                        System.out.print(" ");
                }
                System.out.print("  ");
                if (colonne == 7) {
                    System.out.print(vide + " " + (plateauAffichage.length - ligne) + " ");
                }
            }
            System.out.println(vide);
        }
        System.out.println("   " + " A  " + " B  " + " C  " + " D  " + " E  " + " F  " + " G  " + " H  ");
    }

    //affichage plateau côté noir
    public static void affichagePlateauNoir(char[][] plateauStockage, String[][] plateauAffichage) {
        //les pièces -> majuscule = noir, miniscule = blanc
        String TOUR = "\u265C";
        String tour = "\u2656";
        String ROI = "\u265A";
        String roi = "\u2654";
        String DAME = "\u265B";
        String dame = "\u2655";
        String FOU = "\u265D";
        String fou = "\u2657";
        String CAVALIER = "\u265E";
        String cavalier = "\u2658";
        String PION = "\u265F";
        String pion = "\u2659";

        //couleur vide
        String vide = "\u001B[0m";

        System.out.println("   " + " H  " + " G  " + " F  " + " E  " + " D  " + " C  " + " B  " + " A  ");
        for (int ligne = 7; ligne >= 0; ligne--) {
            for (int colonne = 7; colonne >= 0; colonne--) {
                if (colonne == 7) {
                    System.out.print(" " +  (plateauAffichage.length - ligne) + " ");
                }
                System.out.print(plateauAffichage[ligne][colonne] + " ");
                switch (plateauStockage[ligne][colonne]) {
                    case 'T':
                        System.out.print(TOUR);
                        break;
                    case 't':
                        System.out.print(tour);
                        break;
                    case 'R':
                        System.out.print(ROI);
                        break;
                    case 'r':
                        System.out.print(roi);
                        break;
                    case 'D':
                        System.out.print(DAME);
                        break;
                    case 'd':
                        System.out.print(dame);
                        break;
                    case 'F':
                        System.out.print(FOU);
                        break;
                    case 'f':
                        System.out.print(fou);
                        break;
                    case 'C':
                        System.out.print(CAVALIER);
                        break;
                    case 'c':
                        System.out.print(cavalier);
                        break;
                    case 'P':
                        System.out.print(PION);
                        break;
                    case 'p':
                        System.out.print(pion);
                        break;
                    default:
                        System.out.print(" ");
                }
                System.out.print("  ");
                if (colonne == 0) {
                    System.out.print(vide + " " + (plateauAffichage.length - ligne) + " ");
                }
            }
            System.out.println(vide);
        }
        System.out.println("   " + " H  " + " G  " + " F  " + " E  " + " D  " + " C  " + " B  " + " A  ");
    }

    public static String caseJoue(){
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String reponseDeplacement;
        int colonneJoue, ligneJoue;

        System.out.println("\nOù voulez-vous vous déplacer ?\n");

        reponseDeplacement = scanner.nextLine();

        if (reponseDeplacement.length() != 2) {

            while (reponseDeplacement.length() != 2) {
                System.out.println("\nLa case est fausse. Où voulez-vous vous déplacer ?\n");

                reponseDeplacement = scanner.nextLine();
            }
        }

        colonneJoue = Deplacement.trouvercolonne(reponseDeplacement);
        ligneJoue = Deplacement.trouverligne(reponseDeplacement);

        while (colonneJoue == -1 || ligneJoue == -1 || reponseDeplacement.length() != 2) {
            System.out.println("\nLa case est fausse. Où voulez-vous vous déplacer ?\n");

            reponseDeplacement = scanner.nextLine();
            colonneJoue = Deplacement.trouvercolonne(reponseDeplacement);
            ligneJoue = Deplacement.trouverligne(reponseDeplacement);
        }
        return reponseDeplacement;
    }

    //change le pion en la pièce choisit
    public static void promotion(int tour, char[][]plateau, int ligne, int colonne, boolean GameMode) {
        String reponse;
        boolean reponseExiste;
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        if (!GameMode) {
            do {
                System.out.println("\nQuel pièce voulez_vous que le pion devienne ? (cavalier, fou, tour, dame) ");
                reponse = scanner.nextLine();
                reponse = reponse.toLowerCase();
                switch (reponse) {
                    case "cavalier", "fou", "tour", "dame":
                        reponseExiste = true;
                        break;
                    default:
                        reponseExiste = false;
                }
            } while (!reponseExiste);

            if (tour % 2 == 0) {
                switch (reponse) {
                    case "cavalier":
                        plateau[ligne][colonne] = 'C';
                        break;
                    case "fou":
                        plateau[ligne][colonne] = 'F';
                        break;
                    case "tour":
                        plateau[ligne][colonne] = 'T';
                        break;
                    case "dame":
                        plateau[ligne][colonne] = 'D';
                        break;
                }
            } else {
                switch (reponse) {
                    case "cavalier":
                        plateau[ligne][colonne] = 'c';
                        break;
                    case "fou":
                        plateau[ligne][colonne] = 'f';
                        break;
                    case "tour":
                        plateau[ligne][colonne] = 't';
                        break;
                    case "dame":
                        plateau[ligne][colonne] = 'd';
                        break;
                }
            }
        }
        else {
            if (tour % 2 != 0) {
                do {
                    System.out.println("\nQuel pièce voulez_vous que le pion devienne ? (cavalier, fou, tour, dame) ");
                    reponse = scanner.nextLine();
                    reponse = reponse.toLowerCase();
                    switch (reponse) {
                        case "cavalier", "fou", "tour", "dame":
                            reponseExiste = true;
                            break;
                        default:
                            reponseExiste = false;
                    }
                } while (!reponseExiste);


                switch (reponse) {
                    case "cavalier":
                        plateau[ligne][colonne] = 'c';
                        break;
                    case "fou":
                        plateau[ligne][colonne] = 'f';
                        break;
                    case "tour":
                        plateau[ligne][colonne] = 't';
                        break;
                    case "dame":
                        plateau[ligne][colonne] = 'd';
                        break;
                }
            }
            else
                plateau[ligne][colonne] = 'D';
        }
    }
}