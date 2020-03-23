package usbcommgui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bob
 */
public class MSP {

    static void encode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


        final byte[] MSP_HEADER = getMspHeap();

	public static final int MSP_IDENT = 100;
	public static final int MSP_STATUS = 101;
	public static final int MSP_RAW_IMU = 102;
	public static final int MSP_SERVO = 103;
	public static final int MSP_MOTOR = 104;
	public static final int MSP_RC = 105;
	public static final int MSP_RAW_GPS = 106;
	public static final int MSP_COMP_GPS = 107;
	public static final int MSP_ATTITUDE = 108;
	public static final int MSP_ALTITUDE = 109;
	public static final int MSP_ANALOG = 110;
	public static final int MSP_RC_TUNING = 111;
	public static final int MSP_PID = 112;
	public static final int MSP_BOX = 113;
	public static final int MSP_MISC = 114;
	public static final int MSP_MOTOR_PINS = 115;
	public static final int MSP_BOXNAMES = 116;
	public static final int MSP_PIDNAMES = 117;
	public static final int MSP_WP = 118;
	public static final int MSP_BOXIDS = 119;
	public static final int MSP_SERVO_CONF = 120;
	public static final int MSP_NAV_STATUS = 121;
	public static final int MSP_NAV_CONFIG = 122;
	public static final int MSP_SET_RAW_RC = 200;
	public static final int MSP_SET_RAW_GPS = 201;
	public static final int MSP_SET_PID = 202;
	public static final int MSP_SET_BOX = 203;
	public static final int MSP_SET_RC_TUNING = 204;
	public static final int MSP_ACC_CALIBRATION = 205;
	public static final int MSP_MAG_CALIBRATION = 206;
	public static final int MSP_SET_MISC = 207;
	public static final int MSP_RESET_CONF = 208;
	public static final int MSP_SET_WP = 209;
	public static final int MSP_SELECT_SETTING = 210;
	public static final int MSP_SET_HEAD = 211;
	public static final int MSP_SET_SERVO_CONF = 212;
	public static final int MSP_SET_MOTOR = 214;

	public static final int MSP_BIND = 240;

	public static final int MSP_EEPROM_WRITE = 250;

	public static final int MSP_DEBUGMSG = 253;
	public static final int MSP_DEBUG = 254;

	public static final int MSP_SET_SERIAL_BAUDRATE = 199;
	public static final int MSP_ENABLE_FRSKY = 198;

	public static final int IDLE = 0, HEADER_START = 1, HEADER_M = 2, HEADER_ARROW = 3, HEADER_SIZE = 4,
			HEADER_CMD = 5, HEADER_ERR = 6;


public byte[] getMspHeap() {

		byte[] r = new byte[3];
		r[0] = "$".getBytes()[0];
		r[1] = "M".getBytes()[0];
		r[2] = "<".getBytes()[0];
		return r;
	}
    
    
//Encoder    
    public byte[] encode(int msp, Character[] payload) {
		byte[] r;
		int rsSize = 6, idx = MSP_HEADER.length;
		if (payload != null) {
			rsSize = rsSize + payload.length;
		}
		r = new byte[rsSize];

		System.arraycopy(MSP_HEADER, 0, r, 0, idx);

		byte checksum = 0;
		byte payLoadSize = (byte) ((payload != null ? (int) (payload.length) : 0) & 0xFF);
		r[idx++] = (payLoadSize);
		checksum ^= (payLoadSize & 0xFF);

		r[idx++] = ((byte) (msp & 0xFF));
		checksum ^= (msp & 0xFF);

		if (payload != null) {
			for (char c : payload) {
				r[idx++] = ((byte) (c & 0xFF));
				checksum ^= (c & 0xFF);
			}
		}
		r[idx++] = (checksum);
		return (r);
	}
    
    
    
}
