package clients.adverts;

public class AdvertsController {

    AdvertsModel model = null;
    AdvertsView view = null;

    public AdvertsController(AdvertsModel model, AdvertsView view) {
        this.model = model;
        this.view = view;
    }

    public void play(){
        model.initMedia();
        model.mediaPlayer.play();
        view.getBtnPlay().setText("Pause music");
        view.getBtnPlay().setVisible(false);
        view.getBtnPause().setVisible(true);


    }
    public void pause(){
        view.getBtnPause().setVisible(false);
        view.getBtnPlay().setVisible(true);
        model.mediaPlayer.pause();
    }
}
