package dddeurope.workshops.views;

import dddeurope.workshops.services.AddGameDto;
import dddeurope.workshops.services.GameService;

public class AddGameView {
    public void submit() {
        AddGameDto dto = null; // Probably get this from a user or from a third-party service
        GameService.addGame(dto);
        //....
    }
}

