package sessao;

public class Sessao {

	private State state;
	private Boolean recebendo_tick;

	public Sessao()
	{
		state = State.nao_aberta;
		recebendo_tick = false;
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void abrirChamada() {
	}

	public void encerrarChamada() {
	}

	public void calculaPresenca() {
	}

	public void receberTick() {
	}

	public void handleEvent(Object... in_colObject) {
		if (in_colObject.length > 0) {
			String sEventName = (String) in_colObject[0];
//			if ((state == State.idle)
//					&& (sEventName.compareTo("detectItemEvent") == 0)) {
//				Item oItem = (Item) in_colObject[1];
//				m_width = (oItem.width + 2) * 2;
//				m_height = (oItem.height * 2) + 2;
//				if (actualHeightIsLowerThanMinCompare()) {
//					recognized = true;
//				} else {
//					recognized = false;
//				}
//				if (recognizedIsTrue()) {
//					state = State.object_recognized;
//					if (widthIsLessThanMinCompare()) {
//						state = State.object_is_too_small;
//					} else if (widthIsGreaterThanMaxCompare()) {
//						state = State.object_is_too_big;
//					} else if (widthIsBetweenMinCompareAndMaxCompare()) {
//						state = State.object_is_fitting;
//					}
//				} else {
//					state = State.object_not_recognized;
//				}
//			}
//			if (sEventName.compareTo("resetEvent") == 0) {
//				// object not recognized
//				if (state == State.object_not_recognized
//						|| state == State.object_is_fitting
//						|| state == State.object_is_too_big
//						|| state == State.object_is_too_small) {
//					state = State.idle;
//				}
//			}
		}
	}
}