package com.martyworm.cards;


import com.martyworm.Handler.Handler;
import com.martyworm.Player.Player;
import com.martyworm.board.tiles.Tile;
import com.martyworm.config.PropertiesManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Card{

    private static final int FULL_CARD_HEIGHT = 353;
    private  static final int FULL_CARD_WIDTH = 245;
    public static final int SMALL_CARD_HEIGHT = 86;
    public static final int SMALL_CARD_WIDTH = 81;


    public static final int ZOOM_SPOT_X = 23;
    public static final int ZOOM_SPOT_Y = 300;

    protected Handler handler;

    private int height, width, xPos, yPos, id;
    private Rectangle hitBox;
    private BufferedImage[] images;
    private BufferedImage image;

    private boolean tapped;
    private boolean hovering;
    protected boolean selected;

    //consider an enum to replace the following 5 booleans.
    private boolean inHand, inGrave, inActive, inDeck;
    protected boolean casted = false;

    private int mana;
    protected int playerNumber;


    public Card(Handler handler, BufferedImage[] images, int id, int playerNumber){
        this.handler = handler;
        this.images = images;
        this.id = id;
        this.playerNumber = playerNumber;
        this.xPos = 16000;
        this.yPos = 16000;
        this.height = SMALL_CARD_HEIGHT;
        this.width = SMALL_CARD_WIDTH;
        this.hitBox = new Rectangle(xPos, yPos, width, height);
        this.tapped = false;
        this.hovering = false;

    }

    public void tick() {

        updateHitBoxPosition();

    }

    public void render(Graphics g){

        if (inHand || inActive) {
            int imageIndex = tapped ? 2 : 1;
            g.drawImage(images[imageIndex], xPos, yPos, width, height, null);

            if (hovering) {
                g.drawImage(images[0], ZOOM_SPOT_X, ZOOM_SPOT_Y, null);
            }
        }

        if(PropertiesManager.getInstance().getProperty("showCardHitbox")) {
		    g.setColor(Color.blue);
		    g.fillRect((xPos), (yPos), hitBox.width, hitBox.height);
        }

    }






    public void updateHitBoxPosition(){
        this.hitBox.x = xPos;
        this.hitBox.y = yPos;
    }


    public void onMouseMove(MouseEvent e) {
        hovering = hitBox.contains(e.getX(), e.getY());
    }

    public void onLeftMouseRelease(MouseEvent e){
        onClick();
        if(!selected || !hoveringOnRedTile()) {
            return;
        }

        cast(selectCastingTile());
        selected = false;
        turnRedTilesOff(playerNumber);
    }

    public void onRightMouseRelease(MouseEvent e){
        selected = false;
        onDeselect();
    }


    private String checkLocation(){ //consider an enumeration rather than many booleans.
        if(inHand) return "In Hand";
        if(inDeck) return "In Deck";
        if(inGrave) return "In Grave";
        if(inActive) return "In Active";
        else return "Nowhere?";
    }

    protected void onClick() {
    }
    protected void onDeselect() {
    }

    protected void cast(Tile t){
    }


    public boolean hoveringOnRedTile(){
        for(Tile t : handler.getBattle().getTileManager().getTiles()){
            if(handler.getMouseController().getHitBox().intersects(t.getHitBox())){
                if(t.isRedHighlight()){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean doesPlayerHaveEnoughMana(Player player){ //this should be a method "canCast" on the player where the card is passed
        return ((player.getMana() - mana) >= 0);
    }

    protected Tile selectCastingTile(){

        return handler.getBattle().getSelectedTile();
    }

    protected void turnTilesRed(int playerId){}

    protected void turnRedTilesOff(int playerId){}

    //GETTERS AND SETTERS

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean isTapped() {
        return tapped;
    }

    public void setTapped(boolean tapped) {
        this.tapped = tapped;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMana(){
        return this.mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }



    public int getId() {
        return id;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isInHand() {
        return inHand;
    }

    public void setInHand(boolean inHand) {
        this.inHand = inHand;
    }

    public boolean isInGrave() {
        return inGrave;
    }

    public void setInGrave(boolean inGrave) {
        this.inGrave = inGrave;
    }

    public boolean isInActive() {
        return inActive;
    }

    public void setInActive(boolean inActive) {
        this.inActive = inActive;
    }

    public boolean isInDeck() {
        return inDeck;
    }

    public void setInDeck(boolean inDeck) {
        this.inDeck = inDeck;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public BufferedImage[] getImages() {
        return images;
    }
}
