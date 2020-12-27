package com.lilithsthrone.game.sex.managers.nirth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.nirth.Varu;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class SMVaru extends SexManagerDefault {

	private SexType varuSexTypePreference;
	private List<CoverableArea> exposeVaruAreas;
	private List<CoverableArea> exposePlayerAreas;
	
	public SMVaru(AbstractSexPosition position,
			SexType varuSexTypePreference,
			List<CoverableArea> exposeVaruAreas,
			List<CoverableArea> exposePlayerAreas,
			Map<GameCharacter, SexSlot> dominants,
			Map<GameCharacter, SexSlot> submissives) {
		super(position,
				dominants,
				submissives);
		this.varuSexTypePreference = varuSexTypePreference;
		this.exposeVaruAreas = exposeVaruAreas;
		this.exposePlayerAreas = exposePlayerAreas;
	}
	
	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
		map.put(Main.game.getNpc(Varu.class), exposeVaruAreas);
		map.put(Main.game.getPlayer(), exposePlayerAreas);
		return map;
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return character.isPlayer() || !Main.sex.isInForeplay(character);
	}
	
	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return character.isPlayer() || !Main.sex.isInForeplay(character);
	}
	
	@Override
	public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
		return OrgasmBehaviour.CREAMPIE;
	}

	@Override
	public SexControl getSexControl(GameCharacter character) {
		if(!Main.sex.isDom(character)) {
			return SexControl.ONGOING_ONLY;
		}
		return super.getSexControl(character);
	}
	
	@Override
	public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
		if(Main.sex.isDom(character)) {
			return varuSexTypePreference;
		}
		return character.getForeplayPreference(targetedCharacter);
	}
	
}
