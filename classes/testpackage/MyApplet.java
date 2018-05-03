
package testpackage;

import javacard.framework.AID;
import javacard.framework.APDU;
import javacard.framework.Applet;
import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.JCSystem;
import javacard.framework.Shareable;
import javacard.framework.Util;
import javacard.security.AESKey;
import javacard.security.Checksum;

import javacard.security.DESKey;
import javacardx.crypto.Cipher;
import uicc.access.FileView;
import uicc.access.UICCConstants;
import uicc.access.UICCException;
import uicc.access.UICCSystem;
import uicc.toolkit.EnvelopeHandler;
import uicc.toolkit.EnvelopeHandlerSystem;
import uicc.toolkit.ProactiveHandler;
import uicc.toolkit.ProactiveHandlerSystem;
import uicc.toolkit.ProactiveResponseHandler;
import uicc.toolkit.ProactiveResponseHandlerSystem;
import uicc.toolkit.TerminalProfile;
import uicc.toolkit.ToolkitConstants;
import uicc.toolkit.ToolkitException;
import uicc.toolkit.ToolkitInterface;
import uicc.toolkit.ToolkitRegistry;
import uicc.toolkit.ToolkitRegistrySystem;
import uicc.usim.toolkit.USATEnvelopeHandler;
import uicc.usim.toolkit.USATEnvelopeHandlerSystem;
import uicc.usim.access.SIMConstants;
import uicc.usim.access.USIMConstants;
/*import uicc.usim.geolocation.GeoLocation;
import uicc.usim.geolocation.GeoLocationBuilder;
import uicc.usim.geolocation.GeoLocationException;
import uicc.usim.geolocation.GeoLocationInfo;*/
import uicc.usim.toolkit.USATTerminalProfile;;

public class MyApplet extends Applet implements ToolkitInterface, ToolkitConstants, uicc.usim.toolkit.ToolkitConstants {

	/**
	 * Create FileView Object for performing the file related operation
	 */
	FileView fileView;

	/**
	 * Instance of the ToolkitRegistry class so that event can be registered for
	 * application
	 */
	ToolkitRegistry tkReg;

	/**
	 * Instance of the Terminal Profile class
	 */
	TerminalProfile terminalProfile;

	private byte menuId;

	private byte[] menuText= {'M','E','N','U'};


	/**
	 * Default Constructor
	 */
	public MyApplet() {
	
				// This method register the applet to Toolkit Registry
		this.register();

		// Initialize the toolkitRegistry Instance
		tkReg = ToolkitRegistrySystem.getEntry();
		
		fileView = UICCSystem.getTheUICCView(JCSystem.CLEAR_ON_RESET);

		// Register for all required event(s)
		tkReg.setEvent(EVENT_FORMATTED_SMS_PP_ENV);
		tkReg.setEvent(EVENT_PROFILE_DOWNLOAD);
		tkReg.setEvent(EVENT_EVENT_DOWNLOAD_MT_CALL);
		tkReg.setEvent(EVENT_EVENT_DOWNLOAD_CALL_CONNECTED);
		tkReg.setEvent(EVENT_CALL_CONTROL_BY_NAA);
		tkReg.setEvent(EVENT_EVENT_DOWNLOAD_CALL_DISCONNECTED);
		tkReg.setEvent(EVENT_EVENT_DOWNLOAD_LOCATION_STATUS);
		tkReg.setEvent(EVENT_EVENT_DOWNLOAD_ACCESS_TECHNOLOGY_CHANGE);
		tkReg.setEvent(EVENT_FIRST_COMMAND_AFTER_ATR);
	
		tkReg.setEvent(EVENT_EXTERNAL_FILE_UPDATE);
		tkReg.setEvent(EVENT_EVENT_DOWNLOAD_USER_ACTIVITY);
		tkReg.setEvent(EVENT_EVENT_DOWNLOAD_IDLE_SCREEN_AVAILABLE);
		tkReg.setEvent(EVENT_EVENT_DOWNLOAD_LANGUAGE_SELECTION);
		tkReg.setEvent(EVENT_EVENT_BROWSING_STATUS);
		tkReg.setEvent(EVENT_EVENT_DOWNLOAD_BROWSER_TERMINATION);
		tkReg.setEvent(EVENT_EVENT_DOWNLOAD_NETWORK_SEARCH_MODE_CHANGE);
		tkReg.setEvent(EVENT_EVENT_DOWNLOAD_LOCAL_CONNECTION);
		tkReg.setEvent(EVENT_MO_SHORT_MESSAGE_CONTROL_BY_SIM);
		tkReg.setEvent(EVENT_APPLICATION_DESELECT);
	
		// TODO: Only for Menu Based Approach, Once POC done, need to be delete
		// Initialize the menu entry for Menus
		menuId = tkReg.initMenuEntry(menuText, (short) 0, (short) 5, (byte) 0x00, false, (byte) 0, (byte) 0);
	}

	public static void install(byte[] bArray, short bOffset, byte bLength) {
		// Since there is no installation Parameter passed during the applet
		// installation so there is no passed parameter required

		// create the class object
		new MyApplet();
	}

	public void process(APDU apdu) throws ISOException {

		if (selectingApplet())
			return;

		byte[] apduBuffer = apdu.getBuffer();

		if (apduBuffer[ISO7816.OFFSET_CLA] == (byte) 0xA0 && apduBuffer[ISO7816.OFFSET_INS] == (byte) 0xA5
				&& apduBuffer[ISO7816.OFFSET_P1] == (byte) 0x00) {
			switch (apduBuffer[ISO7816.OFFSET_P2]) {
			default:
				break;
			}
		}
	}

	public Shareable getShareableInterfaceObject(AID client_aid, byte parameter) {
		return this;
	}

	public void processToolkit(short event) throws ToolkitException {
		EnvelopeHandler envHdlr;
		ProactiveHandler proHdlr;
		USATEnvelopeHandler usatHdlr;

		switch (event) 
		{
		}
	}

}
