package com.tr.game.state.login;

import com.jogamp.newt.event.KeyEvent;
import com.tr.engine.components.ITRInputField;
import com.tr.engine.components.TRComponentManager;
import com.tr.engine.components.TRLabel;
import com.tr.engine.components.TRTextButton;
import com.tr.engine.gameobject.AbstractGameObject;
import com.tr.engine.grf.Color;
import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.TRScene;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.img.TRImage;
import com.tr.engine.input.ITRKeyListener;
import com.tr.engine.input.TRKeyEvent;
import com.tr.engine.obj.state.TRGameStateManager;
import com.tr.engine.sound.AudioMaster;
import com.tr.game.core.states.TamaryuGameStateFactory;
import com.tr.util.LanguageTranslator;

class LoginInputs extends AbstractGameObject implements ITRKeyListener{

	private ITRInputField username;
	private ITRInputField password;

	private TRTextButton loginButton;
	private boolean stateChange = false;

	private int totalHeight;
	private int totalWidth;

	private TRGLImageView container;


	public LoginInputs(TRScene s) {
		super(0,0,0, 0);

		this.username = TRComponentManager.getInputField();
		this.password = TRComponentManager.getInputField();
		username.setAlignment(TRLabel.CENTER);
		password.setAlignment(TRLabel.CENTER);
		username.setColor(new Color(0,0,0,0));
		password.setColor(new Color(0,0,0,0));
		username.setText("a");
		username.setSize(250, 38);
		username.setText("");
		username.setPosition(150, 155, 8);
		password.setText("a");
		password.setSize(250, 38);
		password.setText("");
		password.setPosition(150, 97, 8);

		this.loginButton = createLoginButton();


		this.container = new TRGLImageView();
		container.setImage(new TRImage("login_bg", "login_dialog", "png", "/img", 0, 0, 0, 510, 314, 510, 314));
		
		initLoginInput(s);
	}
	
	public void update(long time){
		if(stateChange){
			TRGameStateManager.setState(TamaryuGameStateFactory.SETTING_STATE);
		}
	}


	private TRTextButton createLoginButton() {
		final TRTextButton temp = TRComponentManager.getTxtButton(LanguageTranslator.getString("login"));
		temp.setFont("Arial", true);
		temp.setPosition(0, totalHeight);
		totalWidth = Math.max(totalWidth, temp.getWidth());
		totalHeight += temp.getHeight();

		temp.addStateChangeAction(TRTextButton.MOUSE_ENTER_ACTION, new Runnable() {
			@Override
			public void run() {
				temp.setColor(new Color(255f/255f, 153f/255f, 18f/255f, 0f));
				AudioMaster.playSource(0);
			}
		});

		temp.addStateChangeAction(TRTextButton.MOUSE_LEAVE_ACTION, new Runnable() {
			@Override
			public void run() {
				temp.setColor(new Color(255f/255f, 255/255f, 255/255f, 0f));
				AudioMaster.stopSource(0);
			}
		});

		temp.addStateChangeAction(TRTextButton.MOUSE_DOWN_ACTION, new Runnable() {
			@Override
			public void run() {
				temp.setColor(new Color(205f/255f, 102f/255f, 9f/255f, 0f));
			}
		});

		temp.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable() {
			@Override
			public void run() {

				AudioMaster.playSource(0);
				stateChange = true;
			}
		});

		return temp;
	}

	private void initLoginInput(TRScene s) {
		int x = 0;
		int y = 0;

		/*this.container.addComponent(usernameLabel);
		modfiyTotalValues(usernameLabel);

		this.container.addComponent(username);
		modfiyTotalValues(username);

		this.container.addComponent(passwordLabel);
		modfiyTotalValues(passwordLabel);

		this.container.addComponent(password);
		modfiyTotalValues(password);*/

		loginButton.setSize(510, loginButton.getHeight());
		loginButton.setAlignment(TRLabel.CENTER);
		loginButton.setPosition(0, 25, 4);
		this.container.addComponent(loginButton);
		this.container.addComponent(username);
		this.container.addComponent(password);
		//modfiyTotalValues(loginButton);

		container.setSize(510, 314);
		container.setFixedPosition(IRenderable.FIXED_POS_CENTER);

		/*x = (totalWidth - loginButton.getWidth()) / 2;
		loginButton.setPosition(x, y, 1);
		y = y + loginButton.getHeight();

		// password.setPosition(0, 0, 1);
		y = y + passwordLabel.getHeight();

		x = 0;
		passwordLabel.setPosition(x, y, 1);
		y = y + passwordLabel.getHeight();

		// username.setPosition(0, 0, 1);
		y = y + usernameLabel.getHeight();

		usernameLabel.setPosition(x, y, 1);*/
	}

	private void modfiyTotalValues(IRenderable rend) {
		if (rend != null) {
			int widthTemp = rend.getWidth();
			totalWidth = (widthTemp > totalWidth) ? widthTemp : totalWidth;
			totalHeight = totalHeight + rend.getHeight();
		}
	}

	public TRGLImageView getContainer() {
		return this.container;
	}

	@Override
	public void keyPressed(TRKeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(TRKeyEvent e) {
		if(e.e.getKeyCode() == KeyEvent.VK_ENTER){
			AudioMaster.playSource(0);
			this.stateChange = true;
		}
	}
}
