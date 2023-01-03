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
        view.getBtnPlay().setVisible(false);
        view.getBtnPause().setVisible(true);


    }
    public void pause(){
        model.mediaPlayer.pause();
        view.getBtnPlay().setVisible(true);
        view.getBtnPause().setVisible(false);

    }
}
