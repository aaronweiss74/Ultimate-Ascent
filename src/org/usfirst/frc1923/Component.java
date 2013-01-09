package org.usfirst.frc1923;

/**
 * The basic interface for system components.
 * @author Aaron Weiss, Bhavish Yalamanchi
 * @version 1.0
 * @since 1/8/13
 */
public interface Component {
	/**
	 * Gets the component's current state.
	 * @return the component's current state
	 */
	public ComponentState getState();
	
	/**
	 * A static class for abstracting away robot states.
	 * @author Aaron Weiss
	 * @version 1.0
	 * @since 1/8/13
	 */
	public final static class ComponentState {
		public int COMPONENT_OFF = 0;
		public int COMPONENT_ON = 1;
		public int COMPONENT_FORWARD = 2;
		public int COMPONENT_REVERSE = 3;
		public int COMPONENT_RESET = 4;
		public int COMPONENT_NEED_RESET = 5;
	}
}
