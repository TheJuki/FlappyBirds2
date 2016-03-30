package com.nargles.flappybird2.gameEnvironment.player;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nargles.flappybird2.assetManager.AssetLoader;
import com.nargles.flappybird2.gameEnvironment.projectiles.Projectile;

import java.util.ArrayList;
import java.util.List;

public class Bird {

	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;

	private float rotation;
	private int width;
	private float height;

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    private List<Projectile> projectiles;

	private float originalY;

	private boolean isAlive;

	private Circle boundingCircle;

    /**
     * Constructor
     * @param x Player X position
     * @param y Player Y position
     * @param width Bird sprite width
     * @param height Bird sprite height
     */
    public Bird(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		this.originalY = y;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 460);
		boundingCircle = new Circle();
		isAlive = true;
        projectiles = new ArrayList<Projectile>();
	}

    /**
     * Update bird direction and y position
     * @param delta Scalar for Acceleration vector
     * @param isRightGoing False if game flipped (Flip bird)
     */
    public void update(float delta, boolean isRightGoing) {

		velocity.add(acceleration.cpy().scl(delta));

		if (velocity.y > 200) {
			velocity.y = 200;
		}

		// CEILING CHECK
		if (position.y < -13) {
			position.y = -13;
			velocity.y = 0;
		}

		position.add(velocity.cpy().scl(delta));

		// Set the circle's center to be (9, 6) with respect to the bird.
		// Set the circle's radius to be 6.5f;
		boundingCircle.set(position.x + 9, position.y + 6, 6.5f);

		if(isRightGoing) {
			// Rotate counterclockwise
			if (velocity.y < 0) {
				rotation -= 600 * delta;
	
				if (rotation < -20) {
					rotation = -20;
				}
			}
	
			// Rotate clockwise
			if (isFalling() || !isAlive) {
				rotation += 480 * delta;
				if (rotation > 90) {
					rotation = 90;
				}
	
			}
		}
		else
		{
			// Rotate clockwise
			if (velocity.y < 0) {
				rotation += 600 * delta;
	
				if (rotation > 20) {
					rotation = 20;
				}
			}
	
			// Rotate counterclockwise
			if (isFalling() || !isAlive) {
				rotation -= 480 * delta;
				if (rotation < -90) {
					rotation = -90;
				}
	
			}
		}

	}

    /**
     * Constantly move bird up and down on the Main Menu
     * @param runTime delta
     */
    public void updateReady(float runTime) {
		position.y = 2 * (float) Math.sin(7 * runTime) + originalY;
	}

    /**
     * Determine if Bird is falling
     * @return Velocity y is above some number
     */
    private boolean isFalling() {
		return velocity.y > 110;
	}

    /**
     * Determine if Bird should not animate
     * @return Bird should not be animating
     */
    public boolean shouldNotFlap() {
		return velocity.y > 70 || !isAlive;
	}

    /**
     * If bird is alive, then flap up on tap/click
     */
    public void onClick() {
		if (isAlive) {
			AssetLoader.flap.play();
			velocity.y = -140;
		}
	}

    /**
     * Move bird to the ground when dead
     */
    public void die() {
		isAlive = false;
		velocity.y = 0;
	}

    /**
     * Gravity takes over
     */
    public void decelerate() {
		acceleration.y = 0;
	}

    /**
     * Revive Bird
     * @param y New Bird y position
     */
    public void onRestart(int y) {
		rotation = 0;
		position.y = y;
		velocity.x = 0;
		velocity.y = 0;
		acceleration.x = 0;
		acceleration.y = 460;
		isAlive = true;
	}

    /**
     * Shoot a projectile from Bird
     */
    public void shoot() {
        Projectile p = new Projectile(position.x + 5, position.y + 5, 12, 12, rotation);
        projectiles.add(p);
    }

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getRotation() {
		return rotation;
	}

	public Circle getBoundingCircle() {
		return boundingCircle;
	}

	public boolean isAlive() {
		return isAlive;
	}


}
