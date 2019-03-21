package olivaAdventures;

public class Main {

    public static void main(String[] args) {

        Player player = new Player();

        Enemy enemy1 = new Enemy(Enemy.typeEnemies.type1);
        Enemy enemy2 = new Enemy(Enemy.typeEnemies.type2);
        Enemy enemy3 = new Enemy(Enemy.typeEnemies.boss);

        System.out.println(
            player.toString() +
            enemy1.toString() +
            enemy2.toString() +
            enemy3.toString()
        );

        player.getHurt();
        player.getHurt();

        System.out.println(
                player.toString()
        );

        player.getHurt();

        System.out.println(
                player.toString()
        );

    }

}
