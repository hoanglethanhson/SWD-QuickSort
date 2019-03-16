package sortvisualiser.screens;

import java.awt.BorderLayout;

import javax.swing.SwingWorker;
import sortvisualiser.MainApp;
import sortvisualiser.SortArray;
import sortvisualiser.algorithms.ISortAlgorithm;

/**
 * The main class for the sort visualiser GUI
 *
 */
public final class SortingVisualiserScreen extends Screen {
    private final SortArray sortArray;
    private final ISortAlgorithm quickSortAlgorithm;

    
    public SortingVisualiserScreen(ISortAlgorithm algorithm, boolean playSounds, MainApp app) {
        super(app);
        setLayout(new BorderLayout());
        sortArray = new SortArray(playSounds);
        add(sortArray, BorderLayout.CENTER);
        quickSortAlgorithm = algorithm;
    }
    
    private void longSleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } 
    }

    private void shuffleAndWait() {
        sortArray.shuffle();
        sortArray.resetColours();
        longSleep();
    }
    
    public void onOpen() {
        //This would block the EventDispatchThread, and so
        //it must run on a worker thread
        SwingWorker<Void, Void> swingWorker = new SwingWorker<Void,Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } 
                shuffleAndWait();
                    
                sortArray.setName(quickSortAlgorithm.getName());
                sortArray.setAlgorithm(quickSortAlgorithm);

                quickSortAlgorithm.runSort(sortArray);
                sortArray.resetColours();
                sortArray.highlightArray();
                sortArray.resetColours();
                longSleep();
                return null;
            }
            
            @Override
            public void done() {
                app.popScreen(); 
            }
        };
        
        swingWorker.execute();
    }
}
