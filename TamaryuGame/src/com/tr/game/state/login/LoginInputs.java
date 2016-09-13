package com.tr.game.state.login;

import com.tr.engine.components.ITRInputField;
import com.tr.engine.components.TRComponentManager;
import com.tr.engine.components.TRLabel;
import com.tr.engine.components.TRTextButton;
import com.tr.engine.grf.Color;
import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.TRScene;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.obj.state.TRGameStateFactory;
import com.tr.engine.obj.state.TRGameStateManager;
import com.tr.engine.sound.AudioMaster;
import com.tr.game.core.states.TamaryuGameStateFactory;
import com.tr.util.LanguageTranslator;

class LoginInputs {
	private TRLabel usernameLabel;
	private TRLabel passwordLabel;

	private ITRInputField username;
	private ITRInputField password;

	private TRTextButton loginButton;

	private int totalHeight;
	private int totalWidth;

	private TRGLImageView container;

	public LoginInputs(TRScene s) {
		this.usernameLabel = TRComponentManager.getLabel(LanguageTranslator.getString("username"));
		this.passwordLabel = TRComponentManager.getLabel(LanguageTranslator.getString("password"));

		this.username = TRComponentManager.getInputField();
		this.password = TRComponentManager.getInputField();

		this.loginButton = createLoginButton();

		this.totalHeight = 0;
		this.totalWidth = 0;

		this.container = new TRGLImageView();

		initLoginInput(s);
	}

	private ITRInputField createInput() {
		return null;
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
				TRGameStateManager.setState(TamaryuGameStateFactory.SETTING_STATE);
			}
		});

		return temp;
	}

	private void initLoginInput(TRScene s) {
		int x = 0;
		int y = 0;

		this.container.addComponent(usernameLabel);
		modfiyTotalValues(usernameLabel);

		this.container.addComponent(username);
		modfiyTotalValues(username);

		this.container.addComponent(passwordLabel);
		modfiyTotalValues(passwordLabel);

		this.container.addComponent(password);
		modfiyTotalValues(password);

		this.container.addComponent(loginButton);
		modfiyTotalValues(loginButton);

		s.addMouseListener(loginButton);

		container.setSize(totalWidth, totalHeight);
		container.setFixedPosition(IRenderable.FIXED_POS_CENTER);

		x = (totalWidth - loginButton.getWidth()) / 2;
		loginButton.setPosition(x, y, 1);
		y = y + loginButton.getHeight();

		// password.setPosition(0, 0, 1);
		y = y + passwordLabel.getHeight();

		x = 0;
		passwordLabel.setPosition(x, y, 1);
		y = y + passwordLabel.getHeight();

		// username.setPosition(0, 0, 1);
		y = y + usernameLabel.getHeight();

		usernameLabel.setPosition(x, y, 1);
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
}
