import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeplacementTest {

    //test fou (le fait que la case existe est vérifié dans une autre méthode, ici on teste seulement les déplacements)

    @Test
    public final void testVerifFou() {

        assertTrue(Deplacement.verifFou(0, 2, -1, 3), "diagonale haut droit");
        assertTrue(Deplacement.verifFou(0, 2, 1, 3), "diagonale bas droit");
        assertTrue(Deplacement.verifFou(0, 2, -1, 1), "diagonale haut gauche");
        assertTrue(Deplacement.verifFou(0, 2, 1, 3), "diagonale bas gauche");
        assertFalse(Deplacement.verifFou(0, 2, 0, 3), "mauvais déplacement");
    }

    @Test
    public final void testVerifPion() {
        char[][] plateauStockage = {
                {'T', 'C', 'F', 'D', 'R', 'F', 'C', 'T'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'f', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'t', 'c', 'f', 'd', 'r', 'f', 'c', 't'}

        };


        assertTrue(Deplacement.verifPion(plateauStockage, 1, 1, 3, 1, 'P'), "cas avancer pour les noirs de 2 lignes");
        assertFalse(Deplacement.verifPion(plateauStockage, 1, 5, 8, 5, 'P'), "cas avancer de plus de 2 ligne pour les noirs");
        assertTrue(Deplacement.verifPion(plateauStockage, 6, 2, 4, 2, 'p'), "cas avancer de 2 ligne pour le pion blanc ");
        assertFalse(Deplacement.verifPion(plateauStockage, 6, 4, 3, 4, 'p'), "cas avancer de plus de 2 lignz pour le pion blanc");
        assertTrue(Deplacement.verifPion(plateauStockage, 1, 6, 2, 6, 'P'), "avancer d'une case pour les noirs");
        assertTrue(Deplacement.verifPion(plateauStockage, 6, 6, 5, 6, 'p'), "avancer d'une case pour les blancs");
        assertFalse(Deplacement.verifPion(plateauStockage, 3, 4, 5, 3, 'P'), "essayer d'avancer de deux case en ayant déja bouger");
        assertFalse(Deplacement.verifPion(plateauStockage, 6, 0, 4, 0, 'p'), "avancer de deux case avec une pièce devant");
    }

    @Test
    public final void testBonnecouleur() {
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
        int tour = 1;
        assertTrue(Deplacement.bonneCouleur(tour, 7, 3, plateauStockage), "pièce de la bonne couleur");
        assertFalse(Deplacement.bonneCouleur(tour, 5, 0, plateauStockage), "case vide");
        assertFalse(Deplacement.bonneCouleur(tour, 1, 0, plateauStockage), "pièce de la mauvaise couleur");

        tour = 2;
        assertTrue(Deplacement.bonneCouleur(tour, 0, 0, plateauStockage), "pièce de la bonne couleur");
        assertFalse(Deplacement.bonneCouleur(tour, 5, 0, plateauStockage), "case vide");
        assertFalse(Deplacement.bonneCouleur(tour, 7, 0, plateauStockage), "pièce de la mauvaise couleur");
    }

    @Test
    public final void testpionNoirmange() {
        char[][] plateauStockage = {
                {'T', 'C', 'F', 'D', 'R', 'F', 'v', 'T'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'v', 'v', 'd', 'v', 'f', 'C', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'p', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'p', 'v'},
                {'p', 'p', 'v', 'p', 'p', 'p', 'v', 'p'},
                {'t', 'c', 'f', 'v', 'r', 'v', 'c', 't'}
        };

        assertTrue(Deplacement.pionNoirquimange(plateauStockage, 1, 3, 2, 4, -1, -1), "pion noir qui mange en bas droite");
        assertTrue(Deplacement.pionNoirquimange(plateauStockage, 1, 3, 2, 2, -1, -1), "pion noir qui mange en bas gauche");
        assertFalse(Deplacement.pionNoirquimange(plateauStockage, 1, 6, 3, 5, -1, -1), "pion noir qui mange son propre pion");
        assertFalse(Deplacement.pionNoirquimange(plateauStockage, 1, 2, 2, 2, -1, -1), "pion noir qui essaie de manger tout droit");

    }

    @Test
    public final void testpionBlancquimange() {

        char[][] plateauStockage = {
                {'T', 'C', 'F', 'v', 'R', 'F', 'C', 'T'},
                {'P', 'P', 'v', 'P', 'P', 'P', 'P', 'P'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'P', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'c', 'v', 'D', 'v', 'v', 'v', 'v', 'v'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'t', 'v', 'f', 'd', 'r', 'v', 'c', 't'}
        };

        assertTrue(Deplacement.pionBlancquimange(plateauStockage, 6, 1, 5, 2, -1, -1), "pion blanc qui mange en diagonale droite");
        assertTrue(Deplacement.pionBlancquimange(plateauStockage, 6, 3, 5, 2, -1, -1), "pion blanc qui mange en diagonale haut gauche");
        assertFalse(Deplacement.pionBlancquimange(plateauStockage, 6, 1, 5, 0, -1, -1), "pion blanc qui essaye de manger son propre pion");
        assertFalse(Deplacement.pionBlancquimange(plateauStockage, 6, 2, 5, 2, -1, -1), "pion blanc qui essaie de manger tout droit");

    }

    @Test
    public final void testverifpiecesurlecheminTour() {
        char[][] plateauStockage = {
                {'T', 'C', 'F', 'D', 'R', 'F', 'C', 'T'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'v', 'v', 'T', 'v', 'P', 'T', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'd', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'t', 'c', 'f', 'd', 'r', 'f', 'c', 't'}

        };

        assertTrue(Deplacement.verifpiecesurlecheminTour(plateauStockage, 2, 2, 2, 6), "pièce qui se trouve sur le chemin de sa ligne");
        assertFalse(Deplacement.verifpiecesurlecheminTour(plateauStockage, 2, 2, 5, 2), "aucune pièce sur son chemin de colonne");
        assertTrue(Deplacement.verifpiecesurlecheminTour(plateauStockage, 2, 5, 5, 5), "pièce sur son chemin en colonne");
        assertFalse(Deplacement.verifpiecesurlecheminTour(plateauStockage, 2, 2, 2, 0), "aucune pièce sur son chemin à gauche");
    }

    @Test
    public final void testverifpiecesurlecheminFou() {
        char[][] plateauStockage = {
                {'T', 'C', 'F', 'D', 'R', 'F', 'C', 'T'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'v', 'f', 'f', 'v', 'f', 'v', 'p', 'v'},
                {'f', 'p', 'p', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'p', 'p', 'v', 'v'},
                {'v', 'v', 'v', 'f', 'r', 'f', 'f', 'v'},
                {'p', 'p', 'f', 'p', 'p', 'p', 'p', 'p'},
                {'t', 'c', 'f', 'd', 'r', 'f', 'c', 't'}
        };

        assertTrue(Deplacement.verifpiecesurlecheminFou(plateauStockage, 2, 1, 4, 3), "pièce sur sa diagonale bas droite");
        assertFalse(Deplacement.verifpiecesurlecheminFou(plateauStockage, 3, 0, 6, 3), "aucune pièce sur sa diagonale bas droite ");
        assertTrue(Deplacement.verifpiecesurlecheminFou(plateauStockage, 2, 2, 4, 0), "pièce sur sa diagonale bas gauche");
        assertFalse(Deplacement.verifpiecesurlecheminFou(plateauStockage, 2, 4, 6, 0), "aucune pièce sur sa diagonale bas gauche ");
        assertTrue(Deplacement.verifpiecesurlecheminFou(plateauStockage, 5, 3, 2, 6), "pièce sur sa diagonale haut droite");
        assertFalse(Deplacement.verifpiecesurlecheminFou(plateauStockage, 5, 5, 3, 7), "aucune pièce sur sa diagonale haut droite");
        assertTrue(Deplacement.verifpiecesurlecheminFou(plateauStockage, 5, 6, 3, 4), "pièce sur sa diagonale haut gauche");
        assertFalse(Deplacement.verifpiecesurlecheminFou(plateauStockage, 6, 2, 4, 0), "aucune pièce sur sa diagonale haut gauche");
    }

    @Test
    public final void testverifpiecesurlecheminDame() {

        char[][] plateauStockage = {
                {'T', 'C', 'F', 'D', 'R', 'F', 'C', 'T'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'d', 'p', 'v', 'v', 'v', 'v', 'v', 'd'},
                {'v', 'p', 'v', 'v', 'v', 'd', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'd', 'v', 'v', 'v'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'t', 'c', 'f', 'd', 'r', 'f', 'c', 't'}
        };
        assertTrue(Deplacement.verifpiecesurlecheminDame(plateauStockage, 2, 0, 2, 2), "pièce sur sa ligne à droite");
        assertFalse(Deplacement.verifpiecesurlecheminDame(plateauStockage, 2, 0, 6, 0), "aucune pièce sur sa colonne en bas");
        assertTrue(Deplacement.verifpiecesurlecheminDame(plateauStockage, 2, 0, 4, 2), "pièce sur sa diagonale bas droite");
        assertFalse(Deplacement.verifpiecesurlecheminDame(plateauStockage, 2, 7, 3, 6), "aucune pièce sur sa diagonale bas gauche");
        assertTrue(Deplacement.verifpiecesurlecheminDame(plateauStockage, 3, 5, 0, 5), "pièce sur sa colonne en haut");
        assertFalse(Deplacement.verifpiecesurlecheminDame(plateauStockage, 5, 4, 3, 4), "aucune pièce sur sa colonne en haut");

    }

    @Test
    public final void testroinoirechec() {
        char[][] plateauStockage = {
                {'T', 'C', 'F', 'D', 'v', 'F', 'C', 'T'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'R', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'f', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'t', 'c', 'f', 'd', 'r', 'f', 'c', 't'}
        };

        assertTrue(Deplacement.roinoirechec(plateauStockage), "roi noir en échec par la tour ennemi");

        char[][] plateauStockage1 = {
                {'T', 'C', 'F', 'D', 'v', 'F', 'C', 'T'},
                {'P', 'P', 'v', 'P', 'P', 'P', 'P', 'P'},
                {'v', 'v', 'v', 'R', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'P', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'd', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'t', 'c', 'f', 'd', 'r', 'f', 'c', 't'}
        };


        assertFalse(Deplacement.roinoirechec(plateauStockage1), "Pion noir qui protège le roi d'un échec par la dame");

        char[][] plateauStockage2 = {
                {'T', 'C', 'F', 'D', 'v', 'F', 'C', 'T'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'v', 'v', 'R', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'P', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'c', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'t', 'c', 'f', 'd', 'r', 'f', 'c', 't'}
        };

        assertTrue(Deplacement.roinoirechec(plateauStockage2), "roi noir en echec par le cavalier ");

    }

    @Test
    public final void testroiblancechec() {
        char[][] plateauStockage = {
                {'T', 'C', 'F', 'D', 'R', 'F', 'C', 'T'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'v', 'F', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'c', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'r', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'f', 'v', 'v'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'t', 'c', 'f', 'd', 'v', 'f', 'c', 't'}

        };

        assertFalse(Deplacement.roiBlancEchec(plateauStockage));
    }

    @Test
    public final void testNbPossibilités() {
        char[][] plateauStockage = {
                {'T', 'C', 'F', 'D', 'R', 'F', 'C', 'T'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'v', 'F', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'c', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'r', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'f', 'v', 'v'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'t', 'c', 'f', 'd', 'r', 'f', 'c', 't'}
        };

        assertEquals(2, Deplacement.nbPossibilités(plateauStockage, 6, 0, 1, -1, -1, true, true, true), "test pion");
        assertEquals(0, Deplacement.nbPossibilités(plateauStockage, 7, 4, 1, -1, -1, true, true, true), "test roi blanc");

        char[][] plateauStockage2 = {
                {'R', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'f', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'r', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'}

        };
        assertEquals(3, Deplacement.nbPossibilités(plateauStockage2, 0, 0, 0, -1, -1, false, false, false), "test roi noir");

        char[][] plateauStockage3 = {
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'R'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'd'},
                {'v', 'v', 'v', 'C', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'F', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'r', 'v', 'v', 'v', 'v', 'v', 'v'}

        };
        assertEquals(0, Deplacement.nbPossibilités(plateauStockage3, 2, 3, 0, 1, 7, true, true, true), "test cavalier noir peut pas bouger car echec et mat");


    }

    @Test
    public final void testpat() {
        char[][] plateauStockage = {
                {'R', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'd', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'r', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'}

        };

        assertFalse(Deplacement.pat(plateauStockage, 1, -1, -1, false, false, false), "tour blanc");
        assertTrue(Deplacement.pat(plateauStockage, 2, -1, -1, false, false, false), "tour noir");

        char[][] plateauStockage2 = {
                {'r', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'D', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'R', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'}

        };

        assertTrue(Deplacement.pat(plateauStockage2, 1, -1, -1, false, false, false), "tour blanc");

        char[][] plateauStockage3 = {
                {'v', 'v', 'v', 'R', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'p', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'r', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'}

        };

        assertTrue(Deplacement.pat(plateauStockage3, 2, -1, -1, false, false, false), "tour noir");

    }

    @Test
    public final void testPossibilités() {

        char[][] plateauStockage = {
                {'v', 'v', 'v', 'R', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'P', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'c', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'r', 'v', 'v', 'v', 'v'}

        };

        assertTrue(Deplacement.Possibilités('c', 5,5,3, 4, 1, plateauStockage, -1, -1,false, false, false, true));
    }

    @Test
    public final void testPositionMorte(){
        char[][] plateauStockage = {
                {'v', 'v', 'v', 'R', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'c', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'r', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'}

        };

        assertTrue(Deplacement.positionMorte(plateauStockage));
    }

    @Test
    public final void testechecetmatnoir(){

        char[][] plateau = {
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'r', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'c', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'C', 'R'}

        };
        assertTrue(Deplacement.echecetmatnoir(0,plateau,-1,-1,false,false,false),"echec et mat par cavalier");

        char[][] plateauStockage1 = {
                {'v', 'v', 'P', 'R', 'P', 'v', 'v', 'v'},
                {'v', 'v', 'P', 'P', 'P', 'v', 'v', 'v'},
                {'v', 'v', 'c', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'd', 'v', 'v', 'v', 'v'}

        };

        assertTrue(Deplacement.echecetmatnoir(0,plateauStockage1,2,2,false,false,false),"clouage mat à l'étouffé avec le cavalier");

        char[][] plateauStockage2 = {
                {'v', 'v', 'v', 'D', 'R', 'F', 'C', 'T'},
                {'v', 'v', 'v', 'P', 'P', 'd', 'P', 'P'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'f', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'}

        };

        assertTrue(Deplacement.echecetmatnoir(0,plateauStockage2,1,5,false,false,false),"mat du berger");

        char[][] plateauStockage3 = {
                {'v', 'v', 'v', 't', 'v', 'v', 'R', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'P', 'P', 'P'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'}

        };
        assertTrue(Deplacement.echecetmatnoir(0,plateauStockage3,0,3,false,false,false),"mat du couloir");
        assertEquals(0, Deplacement.nbPossibilités(plateauStockage3,0,6,0,-4,9,false,false,false));

        char[][] plateauStockage4 = {
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'R'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 't'},
                {'v', 'v', 'v', 'v', 'v', 'c', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'r', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'}

        };

        assertTrue(Deplacement.echecetmatnoir(0,plateauStockage4,1,7,false,false,false));

        char[][] plateauStockage5 = {
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'R'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'd', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'r', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'}

        };

        assertTrue(Deplacement.echecetmatnoir(0,plateauStockage5,-1,-1,false,false,false),"baiser de la mort");

        char[][] plateauStockage6 = {
                {'t', 'v', 'v', 'v', 'v', 'v', 'v', 'R'},
                {'v', 't', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'r', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'T', 'v'}

        };

        assertFalse(Deplacement.echecetmatnoir(0,plateauStockage6,-1,-1,false,false,false),"tour noir qui empeche le mat ");

        char[][] plateauStockage7 = {
                {'t', 'v', 'v', 'v', 'v', 'v', 'v', 'R'},
                {'v', 't', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'r', 'v', 'C'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'v', 'v', 'v', 'v', 'v', 'v', 'v', 'v'},
                {'r', 'v', 'v', 'v', 'v', 'v', 'v', 'v'}

        };

        assertFalse(Deplacement.echecetmatnoir(0,plateauStockage7,-1,-1,false,false,false),"cavalier noir qui empeche le mat de la tour ligne 1");


    }


}