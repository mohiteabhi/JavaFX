import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class c2w_WebBrowser extends Application {
    public static void main(String[] c2w_args) {
        launch(c2w_args);
    }

    private ArrayList<c2w_BrowserWindow> c2w_openWindows;
    private Rectangle2D c2w_screenRect;
    private double c2w_locationX, c2w_locationY;
    private double c2w_windowWidth, c2w_windowHeight;
    private int c2w_untitledCount;

    /*
     * Opens a window that will load the c2w_URL
     * https://www.core2web.in/privacypolicy.html
     * (the front page of the textbook in which this program is an
     * example).
     * Note that the Stage parameter to this method is never used.
     */

    public void start(Stage stage) {
        c2w_openWindows = new ArrayList<c2w_BrowserWindow>(); // List of
        c2w_screenRect = Screen.getPrimary().getVisualBounds();

        // (c2w_locationX,c2w_locationY) will be the location of the upper left

        // corner of the next window to be opened. For the first window,

        // the window is moved a little down and over from the top-left
        // corner of the primary screen's visible bounds.
        c2w_locationX = c2w_screenRect.getMinX() + 30;
        c2w_locationY = c2w_screenRect.getMinY() + 20;

        // The window size depends on the height and width of thescreen's

        // visual bounds, allowing some extra space so that it will be
        // possible to stack several windows, each displaced from the
        // previous one. (For aesthetic reasons, limit the width to be
        // at most 1.6 times the height.)
        c2w_windowHeight = c2w_screenRect.getHeight() - 160;
        c2w_windowWidth = c2w_screenRect.getWidth() - 130;
        if (c2w_windowWidth > c2w_windowHeight * 1.6) {
            c2w_windowWidth = c2w_windowHeight * 1.6;
        }
        // Open the first window, showing the front page of this textbook.
        c2w_newBrowserWindow("https://www.core2web.in/privacypolicy.html");

    }

    ArrayList<c2w_BrowserWindow> getOpenWindowList() {
        return c2w_openWindows;
    }

    int c2w_getNextUntitledCount() {
        return ++c2w_untitledCount;
    }

    void c2w_newBrowserWindow(String c2w_url) {
        c2w_BrowserWindow window = new c2w_BrowserWindow(this, c2w_url);
        c2w_openWindows.add(window);

        window.setOnHidden(e -> {
            c2w_openWindows.remove(window);
            System.out.println("Number of open windows is " + c2w_openWindows.size());
            if (c2w_openWindows.size() == 0) {
                System.out.println("Program will end because all windows have been closed");
            }
        });
        if (c2w_url == null) {
            window.setTitle("c2w_Untitled " + c2w_getNextUntitledCount());
        }
        window.setX(c2w_locationX); // set location and size of the window

        window.setY(c2w_locationY);
        window.setWidth(c2w_windowWidth);
        window.setHeight(c2w_windowHeight);
        window.show();
        c2w_locationX += 30; // set up location of NEXT window
        c2w_locationY += 20;
        if (c2w_locationX + c2w_windowWidth + 10 > c2w_screenRect.getMaxX()) {
            c2w_locationX = c2w_screenRect.getMinX() + 30;
        }
        if (c2w_locationY + c2w_windowHeight + 10 > c2w_screenRect.getMaxY()) {
            c2w_locationY = c2w_screenRect.getMinY() + 20;
        }
    }

}
