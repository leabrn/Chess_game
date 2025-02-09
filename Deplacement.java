public class Deplacement {

    // trouve la colonne de la case de l'utilisateur
    public static int trouvercolonne(String caseutilisateur) {
        char lettre;
        int colonne;
        caseutilisateur = caseutilisateur.toUpperCase();
        lettre = caseutilisateur.charAt(0);
        switch (lettre) {
            case 'A':
                colonne = 0;
                break;
            case 'B':
                colonne = 1;
                break;
            case 'C':
                colonne = 2;
                break;
            case 'D':
                colonne = 3;
                break;
            case 'E':
                colonne = 4;
                break;
            case 'F':
                colonne = 5;
                break;
            case 'G':
                colonne = 6;
                break;
            case 'H':
                colonne = 7;
                break;
            default:
                colonne = -1;
        }
        return colonne;
    }


    // trouve la ligne de la case de l'utilisateur
    public static int trouverligne(String caseutilisateur) {
        int ligne;
        char chiffre;
        chiffre = caseutilisateur.charAt(1);
        switch (chiffre) {
            case '8':
                ligne = 0;
                break;
            case '7':
                ligne = 1;
                break;
            case '6':
                ligne = 2;
                break;
            case '5':
                ligne = 3;
                break;
            case '4':
                ligne = 4;
                break;
            case '3':
                ligne = 5;
                break;
            case '2':
                ligne = 6;
                break;
            case '1':
                ligne = 7;
                break;
            default:
                ligne = -1;
        }
        return ligne;
    }

    //verifier que la pièce est de la bonne couleur
    public static boolean bonneCouleur(int tourJoue, int ligne, int colonne, char[][] plateau) {
        char pionJoue = plateau[ligne][colonne];
        if (tourJoue % 2 == 0) {
            if (pionJoue == 'P'
                    || pionJoue == 'T'
                    || pionJoue == 'R'
                    || pionJoue == 'D'
                    || pionJoue == 'F'
                    || pionJoue == 'C')
                return true;
        } else {
            if (pionJoue == 'p'
                    || pionJoue == 't'
                    || pionJoue == 'r'
                    || pionJoue == 'd'
                    || pionJoue == 'f'
                    || pionJoue == 'c')
                return true;
        }
        return false;
    }


    //vérification déplacement du fou
    public static boolean verifFou(int ligneFou, int colonneFou, int ligneDeplacement, int colonneDeplacement) {
        for (int i = 0; i < 8; i++) {
            if ((colonneFou + i == colonneDeplacement)
                    && (ligneFou + i == ligneDeplacement)) {
                return true;
            }

            if ((colonneFou - i == colonneDeplacement)
                    && (ligneFou - i == ligneDeplacement)) {
                return true;
            }

            if ((colonneFou + i == colonneDeplacement)
                    && (ligneFou - i == ligneDeplacement)) {
                return true;
            }

            if ((colonneFou - i == colonneDeplacement)
                    && (ligneFou + i == ligneDeplacement)) {
                return true;
            }

        }
        return false;
    }


    //vérification déplacement de la tour
    public static boolean verifTour(int ligneTour, int colonneTour, int ligneDeplacement, int colonneDeplacement) {
        for (int i = 0; i < 8; i++) {
            if ((colonneTour + i == colonneDeplacement)
                    && (ligneTour == ligneDeplacement)) {
                return true;
            }

            if ((colonneTour - i == colonneDeplacement)
                    && (ligneTour == ligneDeplacement)) {
                return true;
            }

            if ((colonneTour == colonneDeplacement)
                    && (ligneTour - i == ligneDeplacement)) {
                return true;
            }

            if ((colonneTour == colonneDeplacement)
                    && (ligneTour + i == ligneDeplacement)) {
                return true;
            }

        }
        return false;
    }

    //vérification déplacement de la dame
    public static boolean verifDame(int ligneDame, int colonneDame, int ligneDeplacement, int colonneDeplacement) {
        if (verifFou(ligneDame, colonneDame, ligneDeplacement, colonneDeplacement)
                || verifTour(ligneDame, colonneDame, ligneDeplacement, colonneDeplacement))
            return true;
        return false;
    }

    //vérification déplacement du roi
    public static boolean verifRoi(int ligneRoi, int colonneRoi, int ligneDeplacement, int colonneDeplacement) {
        if ((colonneRoi + 1 == colonneDeplacement)
                && (ligneRoi == ligneDeplacement)) {
            return true;
        }

        if ((colonneRoi - 1 == colonneDeplacement)
                && (ligneRoi == ligneDeplacement)) {
            return true;
        }

        if ((colonneRoi == colonneDeplacement)
                && (ligneRoi - 1 == ligneDeplacement)) {
            return true;
        }

        if ((colonneRoi == colonneDeplacement)
                && (ligneRoi + 1 == ligneDeplacement)) {
            return true;
        }

        if ((colonneRoi + 1 == colonneDeplacement)
                && (ligneRoi + 1 == ligneDeplacement)) {
            return true;
        }

        if ((colonneRoi - 1 == colonneDeplacement)
                && (ligneRoi - 1 == ligneDeplacement)) {
            return true;
        }

        if ((colonneRoi + 1 == colonneDeplacement)
                && (ligneRoi - 1 == ligneDeplacement)) {
            return true;
        }

        if ((colonneRoi - 1 == colonneDeplacement)
                && (ligneRoi + 1 == ligneDeplacement)) {
            return true;
        }
        return false;
    }

    //vérification déplacement du cavalier
    public static boolean verifCavalier(int ligneCavalier, int colonneCavalier, int ligneDeplacement, int colonneDeplacement) {
        for (int ligne = -2; ligne <= 2; ligne += 4) {
            for (int colonne = -1; colonne <= 1; colonne += 2) {
                if (ligneCavalier + ligne == ligneDeplacement
                        && colonneCavalier + colonne == colonneDeplacement)
                    return true;
                if (ligneCavalier + colonne == ligneDeplacement
                        && colonneCavalier + ligne == colonneDeplacement)
                    return true;
            }
        }
        return false;
    }

    //vérification déplacement du pion
    public static boolean verifPion(char[][] plateaustockage, int lignePion, int colonnePion, int ligneDeplacement, int colonneDeplacement, char pion) {
        boolean peutSeDePlacer = false;
        if (pion == 'P'
                && colonnePion == colonneDeplacement
                && lignePion + 1 == ligneDeplacement) {
            peutSeDePlacer = true;
        } else if (pion == 'p'
                && colonnePion == colonneDeplacement
                && lignePion - 1 == ligneDeplacement) {
            peutSeDePlacer = true;
        } else if (pion == 'P'
                && colonnePion == colonneDeplacement
                && lignePion == 1
                && lignePion + 2 == ligneDeplacement) {
            for (int ligne = lignePion + 1; ligne < ligneDeplacement; ligne++) {
                if (plateaustockage[ligne][colonnePion] == 'v') {
                    peutSeDePlacer = true;
                }
            }
        } else if (pion == 'p'
                && colonnePion == colonneDeplacement
                && lignePion == 6
                && lignePion - 2 == ligneDeplacement) {
            for (int ligne = lignePion - 1; ligne > ligneDeplacement; ligne--) {
                if (plateaustockage[ligne][colonnePion] == 'v') {
                    peutSeDePlacer = true;
                }
            }
        }
        return peutSeDePlacer;
    }


    // déplace le pion à l'endroit souhaiter
    public static void deplacementPiece(char[][] plateaustockage, int lignepion, int colonnepion, int lignedeplacementpion, int colonnedeplacementpion) {

        char pivot;
        pivot = plateaustockage[lignepion][colonnepion];
        plateaustockage[lignepion][colonnepion] = plateaustockage[lignedeplacementpion][colonnedeplacementpion];
        plateaustockage[lignedeplacementpion][colonnedeplacementpion] = pivot;

    }

    // met  la case à vide si l'utilisateur décide de poser sa pièce sur une case où il y a un pièce adverse
    public static void mangerpièce(char[][] plateaustockage, int ligneDeplacement, int colonneDeplacement) {
        if (plateaustockage[ligneDeplacement][colonneDeplacement] != 'v') {
            plateaustockage[ligneDeplacement][colonneDeplacement] = 'v';
        }
    }


    //vérifie qu'un pion noir mange bien un pion blanc
    public static boolean pionNoirquimange(char[][] plateaustockage, int lignepion, int colonnepion, int lignedeplacement, int colonnedeplacement, int derniereLigneutilisateur, int derniereColonneutilisateur) {

        boolean peutmanger = false;

        if (lignepion + 1 == lignedeplacement && colonnepion + 1 == colonnedeplacement) {//diagonale bas droite
            if (plateaustockage[lignedeplacement][colonnedeplacement] != 'v' &&
                    plateaustockage[lignedeplacement][colonnedeplacement] != 'T' &&
                    plateaustockage[lignedeplacement][colonnedeplacement] != 'C' &&
                    plateaustockage[lignedeplacement][colonnedeplacement] != 'F' &&
                    plateaustockage[lignedeplacement][colonnedeplacement] != 'D' &&
                    plateaustockage[lignedeplacement][colonnedeplacement] != 'P' &&
                    plateaustockage[lignedeplacement][colonnedeplacement] != 'R') {
                peutmanger = true;
            } else if (plateaustockage[lignedeplacement][colonnedeplacement] == 'v'
                    && lignedeplacement == 5
                    && plateaustockage[lignedeplacement - 1][colonnedeplacement] == 'p'
                    && derniereColonneutilisateur == colonnedeplacement
                    && derniereLigneutilisateur == 6) {
                plateaustockage[lignedeplacement - 1][colonnedeplacement] = 'v';
                peutmanger = true;
            }
        } else if (lignepion + 1 == lignedeplacement && colonnepion - 1 == colonnedeplacement) {//diagonale bas gauche
            if (plateaustockage[lignedeplacement][colonnedeplacement] != 'v'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'T'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'C'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'F'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'D'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'P'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'R') {
                peutmanger = true;
            } else if (plateaustockage[lignedeplacement][colonnedeplacement] == 'v'
                    && lignedeplacement == 5
                    && plateaustockage[lignedeplacement - 1][colonnedeplacement] == 'p'
                    && derniereLigneutilisateur == 6
                    && derniereColonneutilisateur == colonnedeplacement) {
                plateaustockage[lignedeplacement - 1][colonnedeplacement] = 'v';
                peutmanger = true;
            }
        }

        return peutmanger;

    }

    //vérifie qu'un pion noir mange bien un pion blanc
    public static boolean pionBlancquimange(char[][] plateaustockage, int lignepion, int colonnepion, int lignedeplacement, int colonnedeplacement, int derniereLigneutilisateur, int derniereColonneutilisateur) {

        boolean peutmanger = false;
        if (lignepion - 1 == lignedeplacement
                && colonnepion - 1 == colonnedeplacement) {//diagonale haut gauche
            if (plateaustockage[lignedeplacement][colonnedeplacement] != 'v'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 't'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'c'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'f'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'd'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'p'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'r') {
                peutmanger = true;
            } else if (plateaustockage[lignedeplacement][colonnedeplacement] == 'v'
                    && lignedeplacement == 2
                    && plateaustockage[lignedeplacement + 1][colonnedeplacement] == 'P'
                    && derniereLigneutilisateur == 1
                    && derniereColonneutilisateur == colonnedeplacement) {
                plateaustockage[lignedeplacement + 1][colonnedeplacement] = 'v';
                peutmanger = true;
            }
        } else if (lignepion - 1 == lignedeplacement
                && colonnepion + 1 == colonnedeplacement) {// diagonale haut droite
            if (plateaustockage[lignedeplacement][colonnedeplacement] != 'v'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 't'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'c'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'f'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'd'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'p'
                    && plateaustockage[lignedeplacement][colonnedeplacement] != 'r') {
                peutmanger = true;
            } else if (plateaustockage[lignedeplacement][colonnedeplacement] == 'v'
                    && lignedeplacement == 2
                    && plateaustockage[lignedeplacement + 1][colonnedeplacement] == 'P'
                    && derniereLigneutilisateur == 1
                    && derniereColonneutilisateur == colonnedeplacement) {
                plateaustockage[lignedeplacement + 1][colonnedeplacement] = 'v';
                peutmanger = true;
            }
        }
        return peutmanger;

    }


    //vérifier si l'action est possible
    public static boolean Possibilités(char pieceUtilisateur, int ligneUtilisateur, int colonneutilisateur, int ligneDeplacement, int colonneDeplacement, int tour, char[][] plateau, int derniereLigneUtilisateur, int derniereColonneutilisateur, boolean PasDeplacementTourGauche, boolean PasDeplacementRoi, boolean PasDeplacementTourDroite, boolean GameMode) {

        char[][] plateauFictif = new char[plateau.length][plateau[0].length];
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                plateauFictif[ligne][colonne] = plateau[ligne][colonne];
            }
        }


        switch (pieceUtilisateur) {
            case 'c':
                if (verifCavalier(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateau)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roiBlancEchec(plateauFictif)) {
                        mangerpièce(plateau, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
            case 'C':
                if (verifCavalier(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateau)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roinoirechec(plateauFictif)) {
                        mangerpièce(plateau, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
            case 'p':
                if (pionBlancquimange(plateau, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement, derniereLigneUtilisateur, derniereColonneutilisateur)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateau)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roiBlancEchec(plateauFictif)) {
                        mangerpièce(plateau, ligneDeplacement, colonneDeplacement);
                        if (ligneDeplacement == 0)
                            Menu.promotion(tour, plateau, ligneUtilisateur, colonneutilisateur, GameMode);
                        return true;
                    }
                } else if (verifPion(plateau, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement, pieceUtilisateur)
                        && plateau[ligneDeplacement][colonneDeplacement] == 'v') {
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roiBlancEchec(plateauFictif)) {
                        if (ligneDeplacement == 0)
                            Menu.promotion(tour, plateau, ligneUtilisateur, colonneutilisateur, GameMode);
                        return true;
                    }
                }
                break;
            case 'P':
                if (pionNoirquimange(plateau, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement, derniereLigneUtilisateur, derniereColonneutilisateur)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateau)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roinoirechec(plateauFictif)) {
                        mangerpièce(plateau, ligneDeplacement, colonneDeplacement);
                        if (ligneDeplacement == 7)
                            Menu.promotion(tour, plateau, ligneUtilisateur, colonneutilisateur, GameMode);
                        return true;
                    }
                } else if (verifPion(plateau, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement, pieceUtilisateur)
                        && plateau[ligneDeplacement][colonneDeplacement] == 'v') {
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roinoirechec(plateauFictif)) {
                        if (ligneDeplacement == 7)
                            Menu.promotion(tour, plateau, ligneUtilisateur, colonneutilisateur, GameMode);
                        return true;
                    }
                }
                break;
            case 'r':
                if (verifRoi(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateau)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roiBlancEchec(plateauFictif)) {
                        mangerpièce(plateau, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                } else if (!roiBlancEchec(plateau)
                        && colonneDeplacement == 2
                        && ligneDeplacement == 7
                        && PasDeplacementTourGauche
                        && PasDeplacementRoi
                        && !verifpiecesurlecheminTour(plateau, 7, 0, ligneUtilisateur, colonneutilisateur)) {
                    plateauFictif[7][3] = 'r';
                    plateauFictif[7][4] = 'v';
                    if (!roiBlancEchec(plateauFictif)) {
                        plateauFictif[7][2] = 'r';
                        plateauFictif[7][3] = 'v';
                        if (!roiBlancEchec(plateauFictif)) {
                            plateau[7][3] = 't';
                            plateau[7][4] = 'r';
                            plateau[7][0] = 'v';
                            plateau[7][2] = 'v';
                            return true;
                        }
                    }
                } else if (!roiBlancEchec(plateau)
                        && colonneDeplacement == 6
                        && ligneDeplacement == 7
                        && PasDeplacementTourDroite
                        && PasDeplacementRoi
                        && !verifpiecesurlecheminTour(plateau, 7, 7, ligneUtilisateur, colonneutilisateur)) {
                    plateauFictif[7][5] = 'r';
                    plateauFictif[7][4] = 'v';
                    if (!roiBlancEchec(plateauFictif)) {
                        plateauFictif[7][6] = 'r';
                        plateauFictif[7][5] = 'v';
                        if (!roiBlancEchec(plateauFictif)) {
                            plateau[7][5] = 't';
                            plateau[7][4] = 'r';
                            plateau[7][7] = 'v';
                            plateau[7][6] = 'v';
                            return true;


                        }
                    }
                }

                break;
            case 'R':
                if (verifRoi(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateau)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roinoirechec(plateauFictif)) {
                        mangerpièce(plateau, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                } else if (!roinoirechec(plateau)
                        && colonneDeplacement == 2
                        && ligneDeplacement == 0
                        && PasDeplacementTourGauche
                        && PasDeplacementRoi
                        && !verifpiecesurlecheminTour(plateau, 0, 0, ligneUtilisateur, colonneutilisateur)) {
                    plateauFictif[0][3] = 'R';
                    plateauFictif[0][4] = 'v';
                    if (!roinoirechec(plateauFictif)) {
                        plateauFictif[0][2] = 'R';
                        plateauFictif[0][3] = 'v';
                        if (!roinoirechec(plateauFictif)) {
                            plateau[0][3] = 'T';
                            plateau[0][4] = 'R';
                            plateau[0][0] = 'v';
                            plateau[0][2] = 'v';
                            return true;


                        }
                    }
                } else if (!roinoirechec(plateau)
                        && colonneDeplacement == 6
                        && ligneDeplacement == 0
                        && PasDeplacementTourDroite
                        && PasDeplacementRoi
                        && !verifpiecesurlecheminTour(plateau, 0, 7, ligneUtilisateur, colonneutilisateur)) {
                    plateauFictif[0][5] = 'R';
                    plateauFictif[0][4] = 'v';
                    if (!roinoirechec(plateauFictif)) {
                        plateauFictif[0][6] = 'R';
                        plateauFictif[0][5] = 'v';
                        if (!roinoirechec(plateauFictif)) {
                            plateau[0][5] = 'T';
                            plateau[0][4] = 'R';
                            plateau[0][7] = 'v';
                            plateau[0][6] = 'v';
                            return true;


                        }
                    }
                }
                break;
            case 'f':
                if (verifFou(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateau)
                        && !verifpiecesurlecheminFou(plateau, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roiBlancEchec(plateauFictif)) {
                        mangerpièce(plateau, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
            case 'F':
                if (verifFou(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateau)
                        && !verifpiecesurlecheminFou(plateau, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roinoirechec(plateauFictif)) {
                        mangerpièce(plateau, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
            case 't':
                if (verifTour(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateau)
                        && !verifpiecesurlecheminTour(plateau, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roiBlancEchec(plateauFictif)) {
                        mangerpièce(plateau, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
            case 'T':
                if (verifTour(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateau)
                        && !verifpiecesurlecheminTour(plateau, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roinoirechec(plateauFictif)) {
                        mangerpièce(plateau, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
            case 'd':
                if (verifDame(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateau)
                        && !verifpiecesurlecheminDame(plateau, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roiBlancEchec(plateauFictif)) {
                        mangerpièce(plateau, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
            case 'D':
                if (verifDame(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateau)
                        && !verifpiecesurlecheminDame(plateau, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roinoirechec(plateauFictif)) {
                        mangerpièce(plateau, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
        }
        return false;
    }


    // vérifie si une pièce se trouve entre la tour et la case où il veut aller
    public static boolean verifpiecesurlecheminTour(char[][] plateaustockage, int ligneTour, int colonneTour, int ligneDeplacement, int colonneDeplacement) {
        boolean piecesurlechemin = false;

        if (ligneTour == ligneDeplacement
                && colonneTour < colonneDeplacement) {//vérifie à droite sur la même ligne
            for (int colonne = colonneTour + 1; colonne < colonneDeplacement; colonne++) {
                if (plateaustockage[ligneTour][colonne] != 'v') {
                    piecesurlechemin = true;
                }
            }
        } else if (ligneTour == ligneDeplacement
                && colonneTour > colonneDeplacement) {//vérifie à gauche sur la même ligne
            for (int colonne = colonneTour - 1; colonne > colonneDeplacement; colonne--) {
                if (plateaustockage[ligneTour][colonne] != 'v') {
                    piecesurlechemin = true;
                }
            }
        } else if (ligneTour < ligneDeplacement
                && colonneTour == colonneDeplacement) {//vérifie en bas sur la même colonne
            for (int ligne = ligneTour + 1; ligne < ligneDeplacement; ligne++) {
                if (plateaustockage[ligne][colonneTour] != 'v') {
                    piecesurlechemin = true;
                }
            }
        } else if (ligneTour > ligneDeplacement
                && colonneTour == colonneDeplacement) {//vérifie en haut sur la même colonne
            for (int ligne = ligneTour - 1; ligne > ligneDeplacement; ligne--) {
                if (plateaustockage[ligne][colonneTour] != 'v') {
                    piecesurlechemin = true;
                }
            }
        }

        return piecesurlechemin;
    }

    // vérifie si une pièce se trouve entre le fou et la case où il veut aller
    public static boolean verifpiecesurlecheminFou(char[][] plateaustockage, int ligneFou, int colonneFou, int ligneDeplacement, int colonneDeplacement) {
        boolean piecesurlechemin = false;
        int différence;

        différence = ligneFou - colonneFou;
        if (ligneFou < ligneDeplacement
                && colonneFou < colonneDeplacement) {//vérifie la diagonale bas droite
            for (int ligne = ligneFou + 1; ligne < ligneDeplacement; ligne++) {
                for (int colonne = colonneFou + 1; colonne < colonneDeplacement; colonne++) {
                    if (ligne - colonne == différence) {
                        if (plateaustockage[ligne][colonne] != 'v') {
                            piecesurlechemin = true;
                        }
                    }
                }
            }
        } else if (ligneFou < ligneDeplacement
                && colonneFou > colonneDeplacement) {//vérifie la diagonale bas gauche
            for (int ligne = ligneFou + 1; ligne < ligneDeplacement; ligne++) {
                différence += 2;
                for (int colonne = colonneFou - 1; colonne > colonneDeplacement; colonne--) {
                    if (ligne - colonne == différence) {
                        if (plateaustockage[ligne][colonne] != 'v') {
                            piecesurlechemin = true;
                        }
                    }
                }
            }
        } else if (ligneFou > ligneDeplacement
                && colonneFou < colonneDeplacement) { //vérifie la diagonale haut droite
            for (int ligne = ligneFou - 1; ligne > ligneDeplacement; ligne--) {
                différence -= 2;
                for (int colonne = colonneFou + 1; colonne < colonneDeplacement; colonne++) {
                    if (ligne - colonne == différence) {
                        if (plateaustockage[ligne][colonne] != 'v') {
                            piecesurlechemin = true;
                        }
                    }
                }
            }
        } else if (ligneFou > ligneDeplacement
                && colonneFou > colonneDeplacement) {//vérifie la diagonale haut gauche
            for (int ligne = ligneFou - 1; ligne > ligneDeplacement; ligne--) {
                for (int colonne = colonneFou - 1; colonne > colonneDeplacement; colonne--) {
                    if (ligne - colonne == différence) {
                        if (plateaustockage[ligne][colonne] != 'v') {
                            piecesurlechemin = true;
                        }
                    }
                }
            }
        }


        return piecesurlechemin;
    }

    // vérifie si une pièce se trouve la dame et la case ou elle veut aller
    public static boolean verifpiecesurlecheminDame(char[][] plateaustockage, int ligneDame, int colonneDame, int ligneDeplacement, int colonneDeplacement) {

        boolean piecesurlechemin = false;

        // vérifie si le déplacement correspond à la tour
        if (verifTour(ligneDame, colonneDame, ligneDeplacement, colonneDeplacement)) {
            if (verifpiecesurlecheminTour(plateaustockage, ligneDame, colonneDame, ligneDeplacement, colonneDeplacement)) {
                piecesurlechemin = true;
            }
        } else if (verifpiecesurlecheminFou(plateaustockage, ligneDame, colonneDame, ligneDeplacement, colonneDeplacement)) {
            piecesurlechemin = true;
        }


        return piecesurlechemin;
    }

    // vérification si le roi noir est en échec
    public static boolean roinoirechec(char[][] plateaustockage) {
        boolean echec = false;

        int ligneRoinoir = 0;
        int colonneRoinoir = 0;

        for (int ligne = 0; ligne < plateaustockage.length; ligne++) {
            for (int colonne = 0; colonne < plateaustockage[ligne].length; colonne++) {
                if (plateaustockage[ligne][colonne] == 'R') {
                    ligneRoinoir = ligne;
                    colonneRoinoir = colonne;
                }
            }
        }

        for (int ligne = 0; ligne < plateaustockage.length; ligne++) {
            for (int colonne = 0; colonne < plateaustockage[ligne].length; colonne++) {
                if (plateaustockage[ligne][colonne] == 'c') {
                    if (verifCavalier(ligne, colonne, ligneRoinoir, colonneRoinoir)) {
                        echec = true;
                    }

                }
                if (plateaustockage[ligne][colonne] == 'f') {
                    if (verifFou(ligne, colonne, ligneRoinoir, colonneRoinoir)) {
                        if (!verifpiecesurlecheminFou(plateaustockage, ligne, colonne, ligneRoinoir, colonneRoinoir)) {
                            echec = true;
                        }
                    }
                }
                if (plateaustockage[ligne][colonne] == 't') {
                    if (verifTour(ligne, colonne, ligneRoinoir, colonneRoinoir)) {
                        if (!verifpiecesurlecheminTour(plateaustockage, ligne, colonne, ligneRoinoir, colonneRoinoir)) {
                            echec = true;
                        }
                    }
                }
                if (plateaustockage[ligne][colonne] == 'd') {
                    if (verifDame(ligne, colonne, ligneRoinoir, colonneRoinoir)) {
                        if (!verifpiecesurlecheminDame(plateaustockage, ligne, colonne, ligneRoinoir, colonneRoinoir)) {
                            echec = true;
                        }
                    }
                }
                if (plateaustockage[ligne][colonne] == 'p') {
                    if (pionBlancquimange(plateaustockage, ligne, colonne, ligneRoinoir, colonneRoinoir, -1, -1)) {
                        echec = true;
                    }
                }
                if (plateaustockage[ligne][colonne] == 'r') {
                    if (verifRoi(ligne, colonne, ligneRoinoir, colonneRoinoir)) {
                        echec = true;
                    }
                }
            }
        }


        return echec;
    }


    // vérification si le roi blanc est en échec
    public static boolean roiBlancEchec(char[][] plateaustockage) {
        boolean echec = false;

        int ligneRoiBlanc = 0;
        int colonneRoiBlanc = 0;


        for (int ligne = 0; ligne < plateaustockage.length; ligne++) {
            for (int colonne = 0; colonne < plateaustockage[ligne].length; colonne++) {
                if (plateaustockage[ligne][colonne] == 'r') {
                    ligneRoiBlanc = ligne;
                    colonneRoiBlanc = colonne;
                }
            }
        }


        for (int ligne = 0; ligne < plateaustockage.length; ligne++) {
            for (int colonne = 0; colonne < plateaustockage[ligne].length; colonne++) {
                if (plateaustockage[ligne][colonne] == 'C') {
                    if (verifCavalier(ligne, colonne, ligneRoiBlanc, colonneRoiBlanc)) {
                        echec = true;
                    }

                }
                if (plateaustockage[ligne][colonne] == 'F') {
                    if (verifFou(ligne, colonne, ligneRoiBlanc, colonneRoiBlanc)) {
                        if (!verifpiecesurlecheminFou(plateaustockage, ligne, colonne, ligneRoiBlanc, colonneRoiBlanc)) {
                            echec = true;
                        }
                    }
                }
                if (plateaustockage[ligne][colonne] == 'T') {
                    if (verifTour(ligne, colonne, ligneRoiBlanc, colonneRoiBlanc)) {
                        if (!verifpiecesurlecheminTour(plateaustockage, ligne, colonne, ligneRoiBlanc, colonneRoiBlanc)) {
                            echec = true;
                        }
                    }
                }
                if (plateaustockage[ligne][colonne] == 'D') {
                    if (verifDame(ligne, colonne, ligneRoiBlanc, colonneRoiBlanc)) {
                        if (!verifpiecesurlecheminDame(plateaustockage, ligne, colonne, ligneRoiBlanc, colonneRoiBlanc)) {
                            echec = true;
                        }
                    }
                }
                if (plateaustockage[ligne][colonne] == 'P') {
                    if (pionNoirquimange(plateaustockage, ligne, colonne, ligneRoiBlanc, colonneRoiBlanc, -1, -1)) {
                        echec = true;
                    }
                }
                if (plateaustockage[ligne][colonne] == 'R') {
                    if (verifRoi(ligne, colonne, ligneRoiBlanc, colonneRoiBlanc)) {
                        echec = true;
                    }
                }
            }
        }


        return echec;
    }


    //permet de calculer le nombre de mouvement pour une pièce
    public static int nbPossibilités(char[][] plateau, int lignePiece, int colonnePiece, int tour, int derniereLigneUtilisateur, int derniereColonneutilisateur, boolean PasDeplacementTourGauche, boolean PasDeplacementRoi, boolean PasDeplacementTourDroite) {
        int nombrePossibilités = 0;
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                if (PossibilitésSansDeplacement(plateau[lignePiece][colonnePiece], lignePiece, colonnePiece, ligne, colonne, tour, plateau, derniereLigneUtilisateur, derniereColonneutilisateur, PasDeplacementTourGauche, PasDeplacementRoi, PasDeplacementTourDroite)) {
                    nombrePossibilités++;
                }
            }
        }
        return nombrePossibilités;
    }

    //renvoie true s'il y a pat
    public static boolean pat(char[][] plateau, int tour, int derniereLigneUtilisateur, int derniereColonneutilisateur, boolean PasDeplacementTourGauche, boolean PasDeplacementRoi, boolean PasDeplacementTourDroite) {

        if (tour % 2 != 0) {
            if (!roiBlancEchec(plateau)) {
                for (int ligne = 0; ligne < plateau.length; ligne++)
                    for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                        if (plateau[ligne][colonne] == 't'
                                || plateau[ligne][colonne] == 'f'
                                || plateau[ligne][colonne] == 'd'
                                || plateau[ligne][colonne] == 'r'
                                || plateau[ligne][colonne] == 'c'
                                || plateau[ligne][colonne] == 'p')
                            if (nbPossibilités(plateau, ligne, colonne, tour, derniereLigneUtilisateur, derniereColonneutilisateur, PasDeplacementTourGauche, PasDeplacementRoi, PasDeplacementTourDroite) > 0)
                                return false;
                    }
            }
            else
                return false;
        } else {
            if (!roinoirechec(plateau)) {
                for (int ligne = 0; ligne < plateau.length; ligne++)
                    for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                        if (plateau[ligne][colonne] == 'T'
                                || plateau[ligne][colonne] == 'F'
                                || plateau[ligne][colonne] == 'D'
                                || plateau[ligne][colonne] == 'R'
                                || plateau[ligne][colonne] == 'C'
                                || plateau[ligne][colonne] == 'P')
                            if (nbPossibilités(plateau, ligne, colonne, tour, derniereLigneUtilisateur, derniereColonneutilisateur, PasDeplacementTourGauche, PasDeplacementRoi, PasDeplacementTourDroite) > 0)
                                return false;
                    }
            }
            else
                return false;
        }
        return true;
    }

    //test les possibilités sans rien manger contrairement à Possibilités
    public static boolean PossibilitésSansDeplacement(char pieceUtilisateur, int ligneUtilisateur, int colonneutilisateur, int ligneDeplacement, int colonneDeplacement, int tour, char[][] plateau, int derniereLigneUtilisateur, int derniereColonneutilisateur, boolean PasDeplacementTourGauche, boolean PasDeplacementRoi, boolean PasDeplacementTourDroite) {

        char[][] plateauFictif = new char[plateau.length][plateau[0].length];
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                plateauFictif[ligne][colonne] = plateau[ligne][colonne];
            }
        }


        switch (pieceUtilisateur) {
            case 'c':
                if (verifCavalier(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateauFictif)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roiBlancEchec(plateauFictif)) {
                        mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
            case 'C':
                if (verifCavalier(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateauFictif)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roinoirechec(plateauFictif)) {
                        mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
            case 'p':
                if (pionBlancquimange(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement, derniereLigneUtilisateur, derniereColonneutilisateur)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateauFictif)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roiBlancEchec(plateauFictif)) {
                        mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                } else if (verifPion(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement, pieceUtilisateur)
                        && plateauFictif[ligneDeplacement][colonneDeplacement] == 'v') {
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roiBlancEchec(plateauFictif)) {
                        return true;
                    }
                }
                break;
            case 'P':
                if (pionNoirquimange(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement, derniereLigneUtilisateur, derniereColonneutilisateur)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateauFictif)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roinoirechec(plateauFictif)) {
                        mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                } else if (verifPion(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement, pieceUtilisateur)
                        && plateauFictif[ligneDeplacement][colonneDeplacement] == 'v') {
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roinoirechec(plateauFictif)) {
                        return true;
                    }
                }
                break;
            case 'r':
                if (verifRoi(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateauFictif)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roiBlancEchec(plateauFictif)) {
                        mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                } else if (!roiBlancEchec(plateauFictif)
                        && colonneDeplacement == 2
                        && ligneDeplacement == 7
                        && PasDeplacementTourGauche
                        && PasDeplacementRoi
                        && !verifpiecesurlecheminTour(plateauFictif, 7, 0, ligneUtilisateur, colonneutilisateur)) {
                    plateauFictif[7][3] = 'r';
                    plateauFictif[7][4] = 'v';
                    if (!roiBlancEchec(plateauFictif)) {
                        plateauFictif[7][2] = 'r';
                        plateauFictif[7][3] = 'v';
                        if (!roiBlancEchec(plateauFictif)) {
                            plateauFictif[7][3] = 't';
                            plateauFictif[7][4] = 'r';
                            plateauFictif[7][0] = 'v';
                            plateauFictif[7][2] = 'v';
                            return true;
                        }
                    }
                } else if (!roiBlancEchec(plateauFictif)
                        && colonneDeplacement == 6
                        && ligneDeplacement == 7
                        && PasDeplacementTourDroite
                        && PasDeplacementRoi
                        && !verifpiecesurlecheminTour(plateauFictif, 7, 7, ligneUtilisateur, colonneutilisateur)) {
                    plateauFictif[7][5] = 'r';
                    plateauFictif[7][4] = 'v';
                    if (!roiBlancEchec(plateauFictif)) {
                        plateauFictif[7][6] = 'r';
                        plateauFictif[7][5] = 'v';
                        if (!roiBlancEchec(plateauFictif)) {
                            plateauFictif[7][5] = 't';
                            plateauFictif[7][4] = 'r';
                            plateauFictif[7][7] = 'v';
                            plateauFictif[7][6] = 'v';
                            return true;


                        }
                    }
                }

                break;
            case 'R':
                if (verifRoi(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateauFictif)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roinoirechec(plateauFictif)) {
                        mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                } else if (!roinoirechec(plateauFictif)
                        && colonneDeplacement == 2
                        && ligneDeplacement == 0
                        && PasDeplacementTourGauche
                        && PasDeplacementRoi
                        && !verifpiecesurlecheminTour(plateauFictif, 0, 0, ligneUtilisateur, colonneutilisateur)) {
                    plateauFictif[0][3] = 'R';
                    plateauFictif[0][4] = 'v';
                    if (!roinoirechec(plateauFictif)) {
                        plateauFictif[0][2] = 'R';
                        plateauFictif[0][3] = 'v';
                        if (!roinoirechec(plateauFictif)) {
                            plateauFictif[0][3] = 'T';
                            plateauFictif[0][4] = 'R';
                            plateauFictif[0][0] = 'v';
                            plateauFictif[0][2] = 'v';
                            return true;


                        }
                    }
                } else if (!roinoirechec(plateauFictif)
                        && colonneDeplacement == 6
                        && ligneDeplacement == 0
                        && PasDeplacementTourDroite
                        && PasDeplacementRoi
                        && !verifpiecesurlecheminTour(plateauFictif, 0, 7, ligneUtilisateur, colonneutilisateur)) {
                    plateauFictif[0][5] = 'R';
                    plateauFictif[0][4] = 'v';
                    if (!roinoirechec(plateauFictif)) {
                        plateauFictif[0][6] = 'R';
                        plateauFictif[0][5] = 'v';
                        if (!roinoirechec(plateauFictif)) {
                            plateauFictif[0][5] = 'T';
                            plateauFictif[0][4] = 'R';
                            plateauFictif[0][7] = 'v';
                            plateauFictif[0][6] = 'v';
                            return true;


                        }
                    }
                }
                break;
            case 'f':
                if (verifFou(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateauFictif)
                        && !verifpiecesurlecheminFou(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roiBlancEchec(plateauFictif)) {
                        mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
            case 'F':
                if (verifFou(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateauFictif)
                        && !verifpiecesurlecheminFou(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roinoirechec(plateauFictif)) {
                        mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
            case 't':
                if (verifTour(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateauFictif)
                        && !verifpiecesurlecheminTour(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roiBlancEchec(plateauFictif)) {
                        mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
            case 'T':
                if (verifTour(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateauFictif)
                        && !verifpiecesurlecheminTour(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roinoirechec(plateauFictif)) {
                        mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
            case 'd':
                if (verifDame(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateauFictif)
                        && !verifpiecesurlecheminDame(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roiBlancEchec(plateauFictif)) {
                        mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
            case 'D':
                if (verifDame(ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)
                        && !bonneCouleur(tour, ligneDeplacement, colonneDeplacement, plateauFictif)
                        && !verifpiecesurlecheminDame(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement)) {
                    mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                    deplacementPiece(plateauFictif, ligneUtilisateur, colonneutilisateur, ligneDeplacement, colonneDeplacement);
                    if (!roinoirechec(plateauFictif)) {
                        mangerpièce(plateauFictif, ligneDeplacement, colonneDeplacement);
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    //calcul le score du bot normal, plus il est élevé plus il y a de pièces noires et moins de blanches sur le plateau
    //si il y a echec blanc le score augmente considérablement et inversement pour l'échec noir
    public static double scorePlateau(char[][] plateau, int ligneDeplacment, int colonneDeplacement) {
        double score = 0;
        if (ligneDeplacment >= 2
                && ligneDeplacment <= 5
                && colonneDeplacement >= 2
                && colonneDeplacement <= 5)
            score += 0.5;

        // Valeur des pièces
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                switch (plateau[ligne][colonne]) {
                    case 'T':
                        score += 5;
                        break;
                    case 'D':
                        score += 9;
                        break;
                    case 'F':
                        score += 3;
                        break;
                    case 'C':
                        score += 3;
                        break;
                    case 'P':
                        score += 1;
                        if (ligne == 7)
                            score += 9;
                        break;
                    case 't':
                        score -= 5;
                        break;
                    case 'd':
                        score -= 9;
                        break;
                    case 'f':
                        score -= 3;
                        break;
                    case 'c':
                        score -= 3;
                        break;
                    case 'p':
                        score -= 1;
                        break;
                }
            }
        }

        if (roinoirechec(plateau))
            score -= 100; //Pénalité important

        if (roiBlancEchec(plateau))
            score += 100; //Bonus important

        return score;
    }

    //renvoie true s'il y a position morte, plus assez de pièces pour jouer
    public static boolean positionMorte(char[][] plateau) {
        int compteurRoiNoir = 0, compteurTourNoire = 0, compteurFouNoir = 0, compteurDameNoire = 0, compteurCavalierNoir = 0, compteurPionNoir = 0;
        int compteurRoiBlanc = 0, compteurTourBlanche = 0, compteurFouBlanc = 0, compteurDameBlanche = 0, compteurCavalierBlanc = 0, compteurPionBlanc = 0;
        int ligneFouBlanc = -1, colonneFouBlanc = -1, ligneFouNoir = -1, colonneFouNoir = -1;
        for (int ligne = 0; ligne < plateau.length; ligne++)
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                switch (plateau[ligne][colonne]) {
                    case 'T':
                        compteurTourNoire += 1;
                        break;
                    case 't':
                        compteurTourBlanche += 1;
                        break;
                    case 'R':
                        compteurRoiNoir += 1;
                        break;
                    case 'r':
                        compteurRoiBlanc += 1;
                        break;
                    case 'D':
                        compteurDameNoire += 1;
                        break;
                    case 'd':
                        compteurDameBlanche += 1;
                        break;
                    case 'F':
                        compteurFouNoir += 1;
                        ligneFouNoir = ligne;
                        colonneFouNoir = colonne;
                        break;
                    case 'f':
                        compteurFouBlanc += 1;
                        ligneFouBlanc = ligne;
                        colonneFouBlanc = colonne;
                        break;
                    case 'C':
                        compteurCavalierNoir += 1;
                        break;
                    case 'c':
                        compteurCavalierBlanc += 1;
                        break;
                    case 'P':
                        compteurPionNoir += 1;
                        break;
                    case 'p':
                        compteurPionBlanc += 1;
                        break;
                }
            }
        if (compteurRoiNoir == 1
                && compteurTourNoire == 0
                && compteurFouNoir == 0
                && compteurDameNoire == 0
                && compteurCavalierNoir == 0
                && compteurPionNoir == 0
                && compteurRoiBlanc == 1
                && compteurTourBlanche == 0
                && compteurFouBlanc == 0
                && compteurDameBlanche == 0
                && compteurCavalierBlanc == 0
                && compteurPionBlanc == 0)
            return true;
        if (compteurRoiNoir == 1
                && compteurTourNoire == 0
                && compteurFouNoir == 1
                && compteurDameNoire == 0
                && compteurCavalierNoir == 0
                && compteurPionNoir == 0
                && compteurRoiBlanc == 1
                && compteurTourBlanche == 0
                && compteurFouBlanc == 0
                && compteurDameBlanche == 0
                && compteurCavalierBlanc == 0
                && compteurPionBlanc == 0)
            return true;
        if (compteurRoiNoir == 1
                && compteurTourNoire == 0
                && compteurFouNoir == 0
                && compteurDameNoire == 0
                && compteurCavalierNoir == 0
                && compteurPionNoir == 0
                && compteurRoiBlanc == 1
                && compteurTourBlanche == 0
                && compteurFouBlanc == 1
                && compteurDameBlanche == 0
                && compteurCavalierBlanc == 0
                && compteurPionBlanc == 0)
            return true;
        if (compteurRoiNoir == 1
                && compteurTourNoire == 0
                && compteurFouNoir == 0
                && compteurDameNoire == 0
                && compteurCavalierNoir == 1
                && compteurPionNoir == 0
                && compteurRoiBlanc == 1
                && compteurTourBlanche == 0
                && compteurFouBlanc == 0
                && compteurDameBlanche == 0
                && compteurCavalierBlanc == 0 && compteurPionBlanc == 0)
            return true;
        if (compteurRoiNoir == 1
                && compteurTourNoire == 0
                && compteurFouNoir == 0
                && compteurDameNoire == 0
                && compteurCavalierNoir == 0
                && compteurPionNoir == 0
                && compteurRoiBlanc == 1
                && compteurTourBlanche == 0
                && compteurFouBlanc == 0
                && compteurDameBlanche == 0
                && compteurCavalierBlanc == 1
                && compteurPionBlanc == 0)
            return true;
        if (compteurRoiNoir == 1
                && compteurTourNoire == 0
                && compteurFouNoir == 1
                && compteurDameNoire == 0
                && compteurCavalierNoir == 0
                && compteurPionNoir == 0
                && compteurRoiBlanc == 1
                && compteurTourBlanche == 0
                && compteurFouBlanc == 1
                && compteurDameBlanche == 0
                && compteurCavalierBlanc == 0
                && compteurPionBlanc == 0) {
            if ((colonneFouBlanc + ligneFouBlanc) % 2 == 0
                    && (colonneFouNoir + ligneFouNoir) % 2 == 0)
                return true;
            else if ((colonneFouBlanc + ligneFouBlanc) % 2 != 0
                    && (colonneFouNoir + ligneFouNoir) % 2 != 0)
                return true;
        }
        return false;
    }

    //renvoie true s'il y a échec et mat noir, donc les blancs gagnent
    public static boolean echecetmatnoir(int tour, char[][] plateau, int derniereLigneUtilisateur, int derniereColonneutilisateur, boolean PasDeplacementTourGauche, boolean PasDeplacementRoi, boolean PasDeplacementTourDroite) {
        int piecesrestantenoir = 1;
        int nbpiecespeutpasbouger = 0;
        boolean echecetmat = false;

        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                if (plateau[ligne][colonne] == 'P'
                        || plateau[ligne][colonne] == 'C'
                        || plateau[ligne][colonne] == 'T'
                        || plateau[ligne][colonne] == 'D'
                        || plateau[ligne][colonne] == 'F') {
                    piecesrestantenoir++;
                }
            }
        }

        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                if (plateau[ligne][colonne] == 'P') {
                    if (nbPossibilités(plateau, ligne, colonne, tour, derniereLigneUtilisateur, derniereColonneutilisateur, PasDeplacementTourGauche, PasDeplacementRoi, PasDeplacementTourDroite) == 0) {
                        nbpiecespeutpasbouger++;
                    }
                }
                if (plateau[ligne][colonne] == 'D') {
                    if (nbPossibilités(plateau, ligne, colonne, tour, derniereLigneUtilisateur, derniereColonneutilisateur, PasDeplacementTourGauche, PasDeplacementRoi, PasDeplacementTourDroite) == 0) {
                        nbpiecespeutpasbouger++;
                    }
                }
                if (plateau[ligne][colonne] == 'R') {
                    if (nbPossibilités(plateau, ligne, colonne, tour, derniereLigneUtilisateur, derniereColonneutilisateur, PasDeplacementTourGauche, PasDeplacementRoi, PasDeplacementTourDroite) == 0) {
                        nbpiecespeutpasbouger++;
                    }
                }
                if (plateau[ligne][colonne] == 'C') {
                    if (nbPossibilités(plateau, ligne, colonne, tour, derniereLigneUtilisateur, derniereColonneutilisateur, PasDeplacementTourGauche, PasDeplacementRoi, PasDeplacementTourDroite) == 0) {
                        nbpiecespeutpasbouger++;
                    }
                }
                if (plateau[ligne][colonne] == 'T') {
                    if (nbPossibilités(plateau, ligne, colonne, tour, derniereLigneUtilisateur, derniereColonneutilisateur, PasDeplacementTourGauche, PasDeplacementRoi, PasDeplacementTourDroite) == 0) {
                        nbpiecespeutpasbouger++;
                    }
                }
                if (plateau[ligne][colonne] == 'F') {
                    if (nbPossibilités(plateau, ligne, colonne, tour, derniereLigneUtilisateur, derniereColonneutilisateur, PasDeplacementTourGauche, PasDeplacementRoi, PasDeplacementTourDroite) == 0) {
                        nbpiecespeutpasbouger++;
                    }
                }
            }
        }

        if (nbpiecespeutpasbouger == piecesrestantenoir) {
            echecetmat = true;
        }

        return echecetmat;
    }

    //renvoie true s'il y a échec et mat blanc, donc les noirs gagnent
    public static boolean echecetmatblanc(int tour, char[][] plateau, int derniereLigneUtilisateur, int derniereColonneutilisateur, boolean PasDeplacementTourGauche, boolean PasDeplacementRoi, boolean PasDeplacementTourDroite) {
        int piecesRestantesBlanches = 1;
        int nbpiecespeutpasbouger = 0;
        boolean echecetmat = false;

        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                if (plateau[ligne][colonne] == 'p'
                        || plateau[ligne][colonne] == 'c'
                        || plateau[ligne][colonne] == 't'
                        || plateau[ligne][colonne] == 'd'
                        || plateau[ligne][colonne] == 'f') {
                    piecesRestantesBlanches++;
                }
            }
        }

        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                if (plateau[ligne][colonne] == 'p') {
                    if (nbPossibilités(plateau, ligne, colonne, tour, derniereLigneUtilisateur, derniereColonneutilisateur, PasDeplacementTourGauche, PasDeplacementRoi, PasDeplacementTourDroite) == 0) {
                        nbpiecespeutpasbouger++;
                    }
                }
                if (plateau[ligne][colonne] == 'd') {
                    if (nbPossibilités(plateau, ligne, colonne, tour, derniereLigneUtilisateur, derniereColonneutilisateur, PasDeplacementTourGauche, PasDeplacementRoi, PasDeplacementTourDroite) == 0) {
                        nbpiecespeutpasbouger++;
                    }
                }
                if (plateau[ligne][colonne] == 'r') {
                    if (nbPossibilités(plateau, ligne, colonne, tour, derniereLigneUtilisateur, derniereColonneutilisateur, PasDeplacementTourGauche, PasDeplacementRoi, PasDeplacementTourDroite) == 0) {
                        nbpiecespeutpasbouger++;
                    }
                }
                if (plateau[ligne][colonne] == 'c') {
                    if (nbPossibilités(plateau, ligne, colonne, tour, derniereLigneUtilisateur, derniereColonneutilisateur, PasDeplacementTourGauche, PasDeplacementRoi, PasDeplacementTourDroite) == 0) {
                        nbpiecespeutpasbouger++;
                    }
                }
                if (plateau[ligne][colonne] == 't') {
                    if (nbPossibilités(plateau, ligne, colonne, tour, derniereLigneUtilisateur, derniereColonneutilisateur, PasDeplacementTourGauche, PasDeplacementRoi, PasDeplacementTourDroite) == 0) {
                        nbpiecespeutpasbouger++;
                    }
                }
                if (plateau[ligne][colonne] == 'f') {
                    if (nbPossibilités(plateau, ligne, colonne, tour, derniereLigneUtilisateur, derniereColonneutilisateur, PasDeplacementTourGauche, PasDeplacementRoi, PasDeplacementTourDroite) == 0) {
                        nbpiecespeutpasbouger++;
                    }
                }
            }
        }

        if (nbpiecespeutpasbouger == piecesRestantesBlanches) {
            echecetmat = true;
        }

        return echecetmat;
    }
}