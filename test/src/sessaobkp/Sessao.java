package sessaobkp;

import java.util.Date;

public class Sessao {

	private State state;
	private Boolean recebendo_tick;
	private Date hora_inicio;
	private Date hora_fim;

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

	public void abrirChamada(State state) {
	}

	public void encerrarChamada(State state) {
	}

	public void calculaPresenca(State state) {
	}

	public void receberTick(State state) {
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