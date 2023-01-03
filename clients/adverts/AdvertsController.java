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

    }
}
