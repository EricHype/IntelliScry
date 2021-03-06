package com.martyworm.entities;

import com.martyworm.Handler.Handler;
import com.martyworm.board.tiles.Tile;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;


public abstract class Entity {

    private static final int DEFAULT_HEALTH = 10;
    public static final int DEFAULT_ENTITY_WIDTH = 65;
    public static final int DEFAULT_ENTITY_HEIGHT = 65;
    private static final int DEFAULT_SPEED = 5;
    public static final int DEFAULT_TILE_MOVEMENT = 4;

    private int id;
    protected int playerId;

    protected Handler handler;
    protected int speed, numActionPoints;
    protected int movesAvailable, distanceMoved;
    protected int xPos, yPos;
    protected int width, height;
    protected int health;
    private boolean active = true;
    protected boolean hovering;
    protected boolean selected = false;
    private int xMove, yMove;
    protected int xStart, yStart;
    protected int pixelsMovedTest;

    //private ArrayList<Tile> movePath;


    protected Rectangle hitBox;

    public Entity(Handler handler, int id, int playerId){
        this.handler = handler;
        this.id = id;
        this.playerId = playerId;
        this.movesAvailable = DEFAULT_TILE_MOVEMENT;
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;


        //hitbox being on xpos and ypos is disabling tile its initialized on hitbox ** FIXED??? ****
        hitBox = new Rectangle((int)xPos/*xPos*/, (int)yPos/*yPos*/, 39/*DEFAULT_ENTITY_HEIGHT*/, 39/*DEFAULT_ENTITY_WIDTH*/);
    }

    //will be each in extended entity
    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void die();

    public abstract void onMouseMove(MouseEvent e);

    public abstract void onLeftMouseRelease(MouseEvent e);

    public abstract void onRightMouseRelease(MouseEvent e);

    public abstract int getXDiff();

    public abstract int getYDiff();

    public abstract void setSelectedTileForMovement(Tile t);

    //If i need an entity to take damage
    public void hurt(int amt){
        health -= amt;
        if(health <= 0){
            active = false;
            die();
        }
    }

    public void moveX(){

    }

    public void moveY(){

    }

    public Tile getOccupiedTileEnt(){
        for(Tile t : handler.getBattle().getTileManager().getTiles()){
            if(t.getHitBox().contains(hitBox)){
                return t;
            }
        }
        return null;
    }

    public Tile getTileByIdEnt(int num){
        for(Tile t : handler.getBattle().getTileManager().getTiles()){
            if(t.getId() == num){
                return t;
            }
        }
        return null;
    }




    public int getXPos() {
        return xPos;
    }

    public void setXPos(int x) {
        this.xPos = x;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int y) {
        this.yPos = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMovesAvailable() {
        return movesAvailable;
    }

    public void setMovesAvailable(int movesAvailable) {
        this.movesAvailable = movesAvailable;
    }

    public boolean isSelected() {
        return selected;
    }

    void setSelected(boolean isSelected) {
        this.selected = isSelected;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public int getxMove() {
        return xMove;
    }

    public void setxMove(int xMove) {
        this.xMove = xMove;
    }

    public int getyMove() {
        return yMove;
    }

    public void setyMove(int yMove) {
        this.yMove = yMove;
    }
    public int getxStart() {
        return xStart;
    }

    public void setxStart(int xStart) {
        this.xStart = xStart;
    }

    public int getyStart() {
        return yStart;
    }

    public void setyStart(int yStart) {
        this.yStart = yStart;
    }


    public int getSpeed() {
        return speed;
    }


    public void setHitBoxX(int x){
        hitBox.x = x;
    }
    public void setHitBoxY(int y){
        hitBox.y = y;
    }

    public boolean isMoving(){
        return xMove > 0 || yMove > 0;
    }

    public int getDistanceMoved() {
        return distanceMoved;
    }

    public void setDistanceMoved(int distanceMoved) {
        this.distanceMoved = distanceMoved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }
    public boolean isHovering() {
        return hovering;
    }
}