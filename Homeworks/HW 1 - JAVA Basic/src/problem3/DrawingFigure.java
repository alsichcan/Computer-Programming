public class DrawingFigure {
    public static void drawFigure(int n) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        String[] figures = new String[6];

        figures[0] = "////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\";
        figures[1] = "////////////////********\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\";
        figures[2] = "////////////****************\\\\\\\\\\\\\\\\\\\\\\\\";
        figures[3] = "////////************************\\\\\\\\\\\\\\\\";
        figures[4] = "////********************************\\\\\\\\";
        figures[5] = "****************************************";

        for(int i = 0; i < n; i++){
            System.out.println(figures[i]);
        }

    }
}
