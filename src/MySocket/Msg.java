package MySocket;

import yy1020.Bullet;

public class Msg {
    private int port;
    public int x;
    public int y;
    public int num;
    public int health;
    public boolean islive;
//    public Bullet bullet[]=new Bullet[5];

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isIslive() {
        return islive;
    }

    public void setIslive(boolean islive) {
        this.islive = islive;
    }

//    public Bullet[] getBullet() {
//        return bullet;
//    }
//
//    public void setBullet(Bullet[] bullet) {
//        this.bullet = bullet;
//    }
}
