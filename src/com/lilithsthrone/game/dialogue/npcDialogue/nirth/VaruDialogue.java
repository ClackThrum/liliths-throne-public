package com.lilithsthrone.game.dialogue.npcDialogue.nirth;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.nirth.Varu;
import com.lilithsthrone.game.character.npc.submission.Axel;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionary;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionarySealed;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMCultistKneeling;
import com.lilithsthrone.game.sex.managers.nirth.SMVaru;
import com.lilithsthrone.game.sex.managers.submission.SMAxel;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotBreedingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.88
 * @version 0.3.7.9
 * @author Innoxia
 */
public class VaruDialogue {

	private static NPC getVaru() {
		return Main.game.getNpc(Varu.class);
	}
	
	public static final DialogueNode ENCOUNTER_START = new DialogueNode("Varu Appears!", "", true) {

		@Override
		public String getContent() {
			// TODO: Varu Dialogue XML
			return "";
		//	return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_START", getVaru());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Make your excuses and get away from this annoying cultist.", ENCOUNTER_START){
					@Override
					public void effects() {
					//	Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_START_LEAVE", getVaru()));
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else if(index==2) {
				return new Response("Follow", "Accept Varu's offer to go back to his place.", ENCOUNTER_VARU) {
					@Override
					public void effects() {
						// Pull up dress:
						//getVaru().displaceClothingForAccess(CoverableArea.PENIS, null);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_VARU = new DialogueNode("Dark Alley", "", true) {

		@Override
		public String getContent() {
			return "";
			//return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL", getVaru());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight", "You're left with no choice but to fight!", getVaru(), Util.newHashMapOfValues(
						new Value<>(Main.game.getPlayer(), "You tell the succubus that you're not interested, and just as you expected, she moves to attack!"),
						new Value<>(getVaru(), "[npc.Name] readies her broomstick and shouts, [npc.speech(How <i>dare</i> you try to refuse my gift! I'll give it to you by force!)]")));
				
			} else if(index==2) {
				return new Response("Accept", "Get ready to service him orally.", ENCOUNTER_VARU_TEASED_1);
			} else if(index == 3) {
				if(Main.game.getPlayer().hasVagina()) {
					return new Response("Offer Pussy", "Offer [npc.name] your pussy instead.", ENCOUNTER_VARU_TEASED_1);
					
				} else {
					return new Response("Offer Pussy", "You'd need a vagina in order to offer it to [npc.name]...", null);
				}
				
			} else if(index==4) {
				return new Response("Offer Ass", "Offer [npc.name] your ass instead.", ENCOUNTER_VARU_TEASED_1);
			
			} else if(index==5) {
				if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isVaginaVirgin()) {
				return new ResponseSex("Offer Vaginal Virginity", "Offer [npc.name] the chance to take your virginity from you.",true, false,
						new SMVaru(
								SexPosition.BREEDING_STALL,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								Util.newArrayListOfValues(CoverableArea.VAGINA),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Varu.class), SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_FRONT))),
						null,
						null, ENCOUNTER_VARU_POST_SUB_VIRGINITY_SEX, UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_LOSS_SEX", getVaru()));
				} else {
				return new Response("Offer Vaginal Virginity", "You'd still need to have your virginity to offer it to [npc.name]...", null);
				}
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNode ENCOUNTER_VARU_POST_SUB_VIRGINITY_SEX = new DialogueNode("Post-sex", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getVaru(), "[npc.Name] has had enough of fucking you...");
		}
		@Override
		public String getContent() {
			return "";
	//		return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_SUB_SEALED_SEX", getVaru());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way.", ENCOUNTER_START){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	public static final DialogueNode ENCOUNTER_VARU_TEASED_1 = new DialogueNode("Teased", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Pull up dress:
			//getVaru().displaceClothingForAccess(CoverableArea.PENIS, null);
		}
		@Override
		public String getContent() {
			// return UtilText.parseFromXMLFile("encounters/nirth/varu", "ENCOUNTER_VARU_TEASED_1", getVaru());
			return "[npc.speech( Actually... before we begin, could you do something for me? It's sort of my fetish.)]";
			//return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_REPEAT", getVaru());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Make your excuses and get away from this annoying cultist.", ENCOUNTER_START){
					@Override
					public void effects() {
					//	Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_START_LEAVE", getVaru()));
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
			}
			else if (index==2) {
			return new Response ("Accept", "Decide to indulge [npc.name]'s request", ENCOUNTER_VARU_HUMILIATED);
			}
			else 
				return null;
			
		}
	};
	
	public static final DialogueNode ENCOUNTER_VARU_HUMILIATED = new DialogueNode("Humiliated", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Pull up dress:
			//getVaru().displaceClothingForAccess(CoverableArea.PENIS, null);
		}
		@Override
		public String getContent() {
			// return UtilText.parseFromXMLFile("encounters/nirth/varu", "ENCOUNTER_VARU_TEASED_1", getVaru());
			return "[npc.speech( You actually did it! You trusted me you moron! You look so silly, hahahaha!)]";
			//return "[npc.speech( Actually... before we begin, could you do something for me? It's sort of my fetish.)]";
			//return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_REPEAT", getVaru());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Humiliated and betrayed, decide to go away.", ENCOUNTER_START){
					@Override
					public void effects() {
						//Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_START_LEAVE", getVaru()));
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
			}
			else if (index==2) {
			//	return null;
				return new ResponseCombat("Fight", "Humiliated and betrayed, you should attack to get back at them!", getVaru(), Util.newHashMapOfValues(
						new Value<>(Main.game.getPlayer(), "You ready your weapon and get into position to attack,"),
						new Value<>(getVaru(), "[npc.Name] readies his weapon and shouts, [npc.speech(I was just messing around! No need to get violent!)]")));
			} else {
				return null;
			}
		}
	};
	
	/*
	public static final DialogueNode ENCOUNTER_CHAPEL_REPEAT = new DialogueNode("The Witch's Chapel", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Pull up dress:
			getVaru().displaceClothingForAccess(CoverableArea.PENIS, null);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_REPEAT", getVaru());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENCOUNTER_VARU.getResponse(0, index);
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_LEAVING = new DialogueNode("The Witch's Chapel", "", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_LEAVING", getVaru());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Leave the chapel and head back out into the streets of Dominion.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
			
			} else if(index==10) {
				return new Response(
						"Remove character",
						"Scare [npc.name] away."
							+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]",
						ENCOUNTER_CHAPEL_LEAVING) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getVaru());
					}
				};
			
			} else {
				return null;
			}
		}
		
		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_COMBAT_VICTORY = new DialogueNode("The Witch's Chapel", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_VICTORY", getVaru());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and head for the door.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public void effects(){
						Colour colour = PresetColour.CLOTHING_BLACK;
						if(getVaru().getClothingInSlot(InventorySlot.TORSO_UNDER)!=null && getVaru().getClothingInSlot(InventorySlot.TORSO_UNDER).getColour(0)==PresetColour.CLOTHING_WHITE) {
							 colour = PresetColour.CLOTHING_WHITE;
						}
						
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots_thigh_high", colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots", colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat", colour, PresetColour.CLOTHING_GOLD, colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", colour, PresetColour.CLOTHING_GOLD, colour, false));
						Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_cleaning_witch_broom"));
					}
				};
			
			} else if(index==2) {
				return new ResponseSex("Sex", "Decide against using [npc.namePos] broomstick to seal [npc.herHim] in place and just have normal, dominant sex with [npc.herHim] instead...",
						true, false,
						new SMAltarMissionary(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(getVaru(), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_DOM_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_VICTORY_SEX", getVaru())) {
				};
			
			} else if(index == 3) {
				return new ResponseSex("Witch's Seal", "Use her broomstick to cast Witch's Seal on her.",
						false, false,
						new SMAltarMissionarySealed(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(getVaru(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
								Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> map = new HashMap<>();
								map.put(ImmobilisationType.WITCH_SEAL, Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Util.newHashSetOfValues(getVaru()))));
								return map;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_DOM_SEALED_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_VICTORY_BROOMSTICK_SEAL_SEX", getVaru())) {
				};
				
			} else if (index == 4) {
				return new Response("Full transformations",
						"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getVaru());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_COMBAT_LOSS = new DialogueNode("The Witch's Chapel", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_LOSS", getVaru());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Witch's Toy", "You're completely immobilised, and can do nothing as the witch prepares to use you as her toy.",
						false, false,
						new SMAltarMissionarySealed(
								Util.newHashMapOfValues(new Value<>(getVaru(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
								Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> map = new HashMap<>();
								map.put(ImmobilisationType.WITCH_SEAL, Util.newHashMapOfValues(new Value<>(getVaru(), Util.newHashSetOfValues(Main.game.getPlayer()))));
								return map;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_SUB_SEALED_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_LOSS_SEX", getVaru())) {
					@Override
					public void effects() {
						((Cultist)getVaru()).setRequestedAnal(false);
						
						// Remove seals so that player can get access to vagina:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
							}
						}
						// Remove seals so that player can get access to anus:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							}
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_ORAL_SEX = new DialogueNode("Post-sex", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getVaru(), "[npc.Name] has had enough of receiving oral sex from you...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_ORAL_SEX", getVaru());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and head for the door.", ENCOUNTER_CHAPEL_POST_ORAL_SEX){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_VAGINAL_SEX = new DialogueNode("Post-sex", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getVaru(), "[npc.Name] has had enough of fucking your pussy...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_VAGINAL_SEX", getVaru());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and head for the door.", ENCOUNTER_CHAPEL_POST_VAGINAL_SEX){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_ANAL_SEX = new DialogueNode("Post-sex", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getVaru(), "[npc.Name] has had enough of fucking your ass...");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_ANAL_SEX", getVaru());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way.", ENCOUNTER_CHAPEL_POST_VAGINAL_SEX){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_SUB_SEALED_SEX = new DialogueNode("Post-sex", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getVaru(), "[npc.Name] has had enough of fucking you...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_SUB_SEALED_SEX", getVaru());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way.", ENCOUNTER_CHAPEL_POST_ORAL_SEX){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_DOM_SEX = new DialogueNode("Post-sex", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getVaru(), "You've had enough of fucking [npc.name]...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_DOM_SEX", getVaru());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and head for the door.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public void effects(){
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots_thigh_high", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_cleaning_witch_broom"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_DOM_SEALED_SEX = new DialogueNode("Post-sex", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_DOM_SEALED_SEX", getVaru());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and head for the door.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public void effects(){
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots_thigh_high", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_cleaning_witch_broom"));
					}
				};
				
			} else {
				return null;
			}
		}
	};*/
} 
