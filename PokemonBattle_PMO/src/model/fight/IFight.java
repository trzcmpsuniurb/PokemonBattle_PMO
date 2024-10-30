package model.fight;

import model.trainer.Trainer;

public interface IFight {

    /**
     * returns the first trainer
     * @return Trainer
     */
    Trainer getTrainer1();

    /**
     * returns the second trainer
     * @return Trainer
     */
    Trainer getTrainer2();

    /**
     * returns the current trainer on play
     * @return Trainer
     */
    Trainer getCurrentTrainer();

    /**
     * returns the non-current trainer out of play
     * @return Trainer
     */
    Trainer getNonCurrentTrainer();

    /**
     * change the turn, switching to the other trainer
     */
    void nextTurn();

    /**
     * return the current fight plan
     * @return int
     */
    int getFightPlan();

    /**
     * switch battle plan between 0 and 1
     * @return int
     */
    int switchPlan();
}

