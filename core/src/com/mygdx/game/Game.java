package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;

public class Game extends ApplicationAdapter {

	SpriteBatch batch;
	Texture img;
	Context context;
	//Pwm pwm;
	DigitalOutputConfigBuilder pinConfig;
	DigitalOutput pin;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		context = Pi4J.newAutoContext();
		pinConfig = DigitalOutput.newConfigBuilder(context)
				.id("led")
				.name("LED Flasher")
				.address(4)
				.shutdown(DigitalState.LOW)
				.initial(DigitalState.LOW)
				.provider("pigpio-digital-output");
		pin = context.create(pinConfig);
		//pwm = context.create(buildPwmConfig(context,12));
		//System.out.println("pwm init");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
