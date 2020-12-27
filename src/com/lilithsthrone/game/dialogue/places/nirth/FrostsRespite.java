package com.lilithsthrone.game.dialogue.places.nirth;

import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.npc.nirth.Milly;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.10
 * @version 0.3.10
 * @author Hallancer
 */
public class FrostsRespite {
	
	
	public static final DialogueNode OUTSIDE = new DialogueNode("Frosts Respite Homestay", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/nirth/frostsRespite/generic", "OUTSIDE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.isExtendedWorkTime()) {
					return new ResponseEffectsOnly("Enter", "Enter the homestay.") {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.FROSTS_RESPITE, PlaceType.NIRTH_FROSTS_RESPITE_ENTRANCE, false);
							
							//Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/generic", "ENTRY"));
						}
					};
				} else {
					return new Response("Enter", "The warehouse is quite clearly closed at the moment. You'll have to come back later if you want to get inside...", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/nirth/frostsRespite/generic", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly("Exit", "Exit the homestay and head back out into Nirth."){
					@Override
					public void effects() {
						
						Main.game.getPlayer().setLocation(WorldType.NIRTH, PlaceType.NIRTH_FROSTS_RESPITE, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/nirth/frostsRespite/generic", "CORRIDOR"));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode WOODEN_FLOORING = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/nirth/frostsRespite/generic", "WOODEN_FLOORING"));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
//			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_MILLY)==Quest.ROMANCE_MILLY_2) {
//				if(index==1) {
//					return new Response millyromancestuff;
//						}
//					};
				return null;
			}
			
		}
	;
	
	
	public static final DialogueNode OFFICE = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/nirth/frostsRespite/generic", "OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index==1) {
			return new Response("Milly", "Talk to Milly.", MILLY_TALK_REPEAT);
			}
			else
				return null;
		}
	};
	
	public static final DialogueNode MILLY_TALK_REPEAT = new DialogueNode("Milly", "-", true) {

		@Override
		public String getAuthor() {
				return "Hallancer";
		}
		
		@Override
		public String getContent() {
			if(Main.game.getNpc(Milly.class).isVisiblyPregnant() && !Main.game.getNpc(Milly.class).isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				// TODO: Milly Pregnant text
				return "Milly is pregnant!";
			} else {
				// TODO: Milly greeting text
				return "Milly greets you.";
		//		return UtilText.parseFromXMLFile("places/nirth/frostsRespite", "MILLY_GREETING_REPEAT");
			}
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Trade";
			} else 
				if(index==1) {
				return "Talk";
			} else {
				return null;
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_MILLY_HELP)) {
					if(index==1) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.millyTalkedTo)) {
							return new Response("Small Talk", "You've already had a chat with Milly today. You can repeat this action tomorrow.", null);
							
						} else {
							return new Response("Small Talk", "Talk with Milly about her homestead, the weather, and other such topics.", ROMANCE_TALK) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Milly.class).incrementAffection(Main.game.getPlayer(), 1f));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.millyTalkedTo, true);
									if(Main.game.getNpc(Milly.class).isVisiblyPregnant()) {
										Main.game.getNpc(Milly.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
						
					} else if(index==2) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.millyComplimented)) {
							return new Response("Compliment", "You've already complimented Milly today. You can repeat this action tomorrow.", null);
							
						} else {
							return new Response("Compliment", "Compliment Milly's appearance and abilities.", ROMANCE_COMPLIMENT) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Milly.class).incrementAffection(Main.game.getPlayer(), 3f));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.millyComplimented, true);
									if(Main.game.getNpc(Milly.class).isVisiblyPregnant()) {
										Main.game.getNpc(Milly.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
						
					} else if(index==3) {
						int requiredAffection = AffectionLevel.POSITIVE_TWO_LIKE.getMinimumValue();
						if(Main.game.getNpc(Milly.class).getAffection(Main.game.getPlayer())<requiredAffection) {
							return new Response(
									"Flirt",
									"You can tell that attempting to flirt with Milly would end in disaster."
										+ " You should work on getting to know her a little better first."
										+ " ([style.italicsBad(Requires Milly's affection to be at least "+requiredAffection+", and is currently "+Main.game.getNpc(Milly.class).getAffection(Main.game.getPlayer())+".)])",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.millyFlirtedWith)) {
							return new Response("Flirt", "You've already flirted with Milly today. You can repeat this action tomorrow.", null);
							
						} else {
							return new Response("Flirt", "Flirt with Milly a little.", ROMANCE_FLIRT) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Milly.class).incrementAffection(Main.game.getPlayer(), 4f));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.millyFlirtedWith, true);
									if(Main.game.getNpc(Milly.class).isVisiblyPregnant()) {
										Main.game.getNpc(Milly.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
						
					} else if(index==4) {
						int requiredAffection = AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue();
						if(Main.game.getNpc(Milly.class).getAffection(Main.game.getPlayer())<requiredAffection) {
							return new Response(
									"Head pat",
									"You can tell that attempting to pat Milly on the head would end in disaster."
										+ " You should work on getting to know her a little better first."
										+ " ([style.italicsBad(Requires Milly's affection to be at least "+requiredAffection+", and is currently "+Main.game.getNpc(Milly.class).getAffection(Main.game.getPlayer())+".)])",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.millyHeadPatted)) {
							return new Response("Head pat", "You've already patted Milly on the head today. You can repeat this action tomorrow.", null);
							
						} else {
							return new Response("Head pat", "Pat Milly on the head and tell her she's a good girl.", ROMANCE_HEAD_PAT) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Milly.class).incrementAffection(Main.game.getPlayer(), 5f));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.millyHeadPatted, true);
									if(Main.game.getNpc(Milly.class).isVisiblyPregnant()) {
										Main.game.getNpc(Milly.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
						
					} else if(index==5) {
						int requiredAffection = AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue();
						if(Main.game.getNpc(Milly.class).getAffection(Main.game.getPlayer())<requiredAffection) {
							return new Response(
									"Kiss",
									"You can tell that attempting to kiss Milly would end in disaster."
										+ " You should work on getting to know her a little better first."
										+ " ([style.italicsBad(Requires Milly's affection to be at least "+requiredAffection+", and is currently "+Main.game.getNpc(Milly.class).getAffection(Main.game.getPlayer())+".)])",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.millyKissed)) {
							return new Response("Kiss", "You've already kissed Milly today. You can repeat this action tomorrow.", null);
							
						} else {
							return new Response("Kiss", "You can tell from the way Milly looks at you that she wouldn't mind being kissed. Lean forwards and give her what she wants.", ROMANCE_KISS) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Milly.class).incrementAffection(Main.game.getPlayer(), 5f));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.millyKissed, true);
									if(Main.game.getNpc(Milly.class).isVisiblyPregnant()) {
										Main.game.getNpc(Milly.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
						
					} else if(index==6) {
						int requiredAffection = AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue();
						if(Main.game.getNpc(Milly.class).getAffection(Main.game.getPlayer())<requiredAffection) {
							return new Response(
									"Make Out",
									"You can tell that attempting to make out with Milly would end in disaster."
										+ " You should work on getting to know her a little better first."
										+ " ([style.italicsBad(Requires Milly's affection to be at least "+requiredAffection+", and is currently "+Main.game.getNpc(Milly.class).getAffection(Main.game.getPlayer())+".)])",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.millyMakeOut)) {
							return new Response("Make Out", "You've already made out with Milly today. You can repeat this action tomorrow.", null);
							
						} else {
							return new Response("Make Out", "You can tell that Milly is desperate for some intense physical contact with you. Lean forwards and passionately start making out with her.", ROMANCE_MAKE_OUT) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Milly.class).incrementAffection(Main.game.getPlayer(), 6f));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.millyMakeOut, true);
									if(Main.game.getNpc(Milly.class).isVisiblyPregnant()) {
										Main.game.getNpc(Milly.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
						
					} else if(index==7) {
						int requiredAffection = AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue();
						
						if(Main.game.getNpc(Milly.class).getAffection(Main.game.getPlayer())<requiredAffection) {
							return new Response(
									"Sex",
									"You can tell that propositioning Milly for sex would end in disaster."
										+ " You should work on getting to know her a little better first."
										+ " ([style.italicsBad(Requires Milly's affection to be at least "+requiredAffection+", and is currently "+Main.game.getNpc(Milly.class).getAffection(Main.game.getPlayer())+".)])",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.millySex)) {
							return new Response("Sex", "You've already had sex with Milly today. You can repeat this action tomorrow.", null);
							
						} else {
							return new ResponseSex("Sex",
									"Have sex with Milly.",
									null, null, null, null, null, null,
									true, true,
									!Main.game.getPlayer().isTaur()
										?new SMLyingDown(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Milly.class), SexSlotLyingDown.LYING_DOWN)))
										:new SMGeneric(
												Util.newArrayListOfValues(Main.game.getPlayer()),
												Util.newArrayListOfValues(Main.game.getNpc(Milly.class)),
												null,
												null),
									null,
									null,
									END_SEX,
									UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_SEX")) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.millySex, true);
									if(Main.game.getNpc(Milly.class).isVisiblyPregnant()) {
										Main.game.getNpc(Milly.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
					} else if(index==10) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.millyGift)) {
							return new Response("Gift", "You've already given Milly a gift today. You can repeat this action tomorrow.", null);
						} else {
							return new Response("Gift", "Give Milly a gift (opens gift selection screen).", ROMANCE_GIFT) {
								@Override
								public DialogueNode getNextDialogue() {
									return GiftDialogue.getGiftDialogue(Main.game.getNpc(Milly.class), ROMANCE_GIFT, 1);
								}
								@Override
								public void effects() {
//									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.millyGift, true);
									if(Main.game.getNpc(Milly.class).isVisiblyPregnant()) {
										Main.game.getNpc(Milly.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
						
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.RELATIONSHIP_MILLY_HELP) == Quest.RELATIONSHIP_MILLY_HOMESTEAD_TASKS_DONE) {
					if(index==1) {
						return new Response("Report back", "Tell Milly that you've dealt with the suppliers.", HOMESTEAD_REPORT_BACK) {
							@Override
							public void effects() {
					//			Main.game.getNpc(Milly.class).setSellModifier(1.25f);
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(5000));
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Milly.class).incrementAffection(Main.game.getPlayer(), 25));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_MILLY_HELP, Quest.SIDE_UTIL_COMPLETE));
							}
						};
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.RELATIONSHIP_MILLY_HELP) == Quest.RELATIONSHIP_MILLY_HOMESTEAD_TASKS_AGREED_TO_HELP) {
					if(index==1) {
						return new Response("Report back", "Report back to Milly once you've dealt with the suppliers.", null);
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.RELATIONSHIP_MILLY_HELP) == Quest.RELATIONSHIP_MILLY_HOMESTEAD_TASKS) {
					if(index==1) {
						return new Response("Offer help", "Tell Milly that you'll help her with her supplier problem.", HOMESTEAD_OFFER_HELP) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Milly.class).incrementAffection(Main.game.getPlayer(), 10));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_MILLY_HELP, Quest.RELATIONSHIP_MILLY_HOMESTEAD_TASKS_AGREED_TO_HELP));
							}
						};
					}
					
				} else if(!Main.game.getPlayer().hasQuest(QuestLine.RELATIONSHIP_MILLY_HELP)){
					if(index==1) {
						return new Response("Hard work", "Ask Milly if she finds it tough to do all of the work.<br/>[style.italicsQuestRomance(This will start Milly's romance quest!)]", HOMESTEAD_OFFER_HELP) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.RELATIONSHIP_MILLY_HELP));
							}
							@Override
							public Colour getHighlightColour() {
								return PresetColour.QUEST_RELATIONSHIP;
							}
						};
					}
				}
				
				
				if (index == 0) {
					return new Response("Leave", "Tell Milly that you've got to get going.", OFFICE) {
						@Override
						public void effects() {
							Main.game.setResponseTab(0);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "MILLY_EXIT"));
							if(Main.game.getNpc(Milly.class).isVisiblyPregnant()) {
								Main.game.getNpc(Milly.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
						}
					};
					
				} else {
					return null;
				}
				
				
			} else if(responseTab==0) {
				if (index == 1) {
					return new ResponseTrade("Food and Drinks", "Ask her what sort of food is offered at the homestead.", Main.game.getNpc(Milly.class)){
						@Override
						public void effects() {
				//			break;
				//			Main.game.getNpc(Milly.class).clearNonEquippedInventory(false);
	
//							for (AbstractClothing c : ((Milly) Main.game.getNpc(Milly.class)).get) {
//								if(Main.game.getNpc(Milly.class).isInventoryFull()) {
//									break;
//								}
//								Main.game.getNpc(Milly.class).addClothing(c, false);
					//		}
						}
					};
					/*
				} else if (index == 2) {
					return new ResponseTrade("Female Lingerie", "Ask her what female lingerie is available.", Main.game.getNpc(Milly.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Milly.class).clearNonEquippedInventory(false);
	
							for (AbstractClothing c : ((Milly) Main.game.getNpc(Milly.class)).getCommonFemaleUnderwear()) {
								if(Main.game.getNpc(Milly.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Milly.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 3) {
					return new ResponseTrade("Female Accessories", "Ask her what female accessories are available.", Main.game.getNpc(Milly.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Milly.class).clearNonEquippedInventory(false);
	
							for (AbstractClothing c : ((Milly) Main.game.getNpc(Milly.class)).getCommonFemaleAccessories()) {
								if(Main.game.getNpc(Milly.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Milly.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 6) {
					return new ResponseTrade("Male Clothing", "Ask her what male clothing is available.", Main.game.getNpc(Milly.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Milly.class).clearNonEquippedInventory(false);
	
							for (AbstractClothing c : ((Milly) Main.game.getNpc(Milly.class)).getCommonMaleClothing()) {
								if(Main.game.getNpc(Milly.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Milly.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 7) {
					return new ResponseTrade("Male Underwear", "Ask her what male underwear is available.", Main.game.getNpc(Milly.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Milly.class).clearNonEquippedInventory(false);
	
							for (AbstractClothing c : ((Milly) Main.game.getNpc(Milly.class)).getCommonMaleLingerie()) {
								if(Main.game.getNpc(Milly.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Milly.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 8) {
					return new ResponseTrade("Male Accessories", "Ask her what male accessories are is available.", Main.game.getNpc(Milly.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Milly.class).clearNonEquippedInventory(false);
	
							for (AbstractClothing c : ((Milly) Main.game.getNpc(Milly.class)).getCommonMaleAccessories()) {
								if(Main.game.getNpc(Milly.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Milly.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 11) {
					return new ResponseTrade("Unisex Clothing", "Ask her what unisex clothing is available.", Main.game.getNpc(Milly.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Milly.class).clearNonEquippedInventory(false);
	
							for (AbstractClothing c : ((Milly) Main.game.getNpc(Milly.class)).getCommonAndrogynousClothing()) {
								if(Main.game.getNpc(Milly.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Milly.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 12) {
					return new ResponseTrade("Unisex Underwear", "Ask her what unisex underwear is available.", Main.game.getNpc(Milly.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Milly.class).clearNonEquippedInventory(false);
	
							for (AbstractClothing c : ((Milly) Main.game.getNpc(Milly.class)).getCommonAndrogynousLingerie()) {
								if(Main.game.getNpc(Milly.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Milly.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 13) {
					return new ResponseTrade("Unisex Accessories", "Ask her what unisex accessories are is available.", Main.game.getNpc(Milly.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Milly.class).clearNonEquippedInventory(false);
	
							for (AbstractClothing c : ((Milly) Main.game.getNpc(Milly.class)).getCommonAndrogynousAccessories()) {
								if(Main.game.getNpc(Milly.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Milly.class).addClothing(c, false);
							}
						}
					};
					*/
				} else if (index == 5) {
					if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_MILLY_HELP)) {
						return new ResponseTrade("Specials", "Ask Milly about any special items of clothing she might have in stock.", Main.game.getNpc(Milly.class)){
							@Override
							public void effects() {
		//						Main.game.getNpc(Milly.class).clearNonEquippedInventory(false);
		
	//							for (AbstractClothing c : ((Milly) Main.game.getNpc(Milly.class)).getSpecials()) {
	//								if(Main.game.getNpc(Milly.class).isInventoryFull()) {
	//									break;
	//								}
	//								Main.game.getNpc(Milly.class).addClothing(c, false);
	//							}
							}
						};
					} else {
						return new Response("Specials", "Milly doesn't have any special items of clothing on offer at the moment...", null);
					}
					
				} else if (index == 0) {
					return new Response("Leave", "Tell Milly that you've got to get going.", OFFICE) {
						@Override
						public void effects() {
				//			return "";
					//		Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "MILLY_EXIT"));
						}
					};
					
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HOMESTEAD_TASKS = new DialogueNode("Milly", "-", true) {

		@Override
		public String getContent() {
			return "Milly says she could use some help";
		//	return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_SUPPLIER_PROBLEM");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return MILLY_TALK_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILLY_TALK_REPEAT.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode ROMANCE_TALK = new DialogueNode("Milly", "-", true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
	//		UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_TALK_BASE"));
		//	UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_TALK"));
		//	UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_TALK_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return MILLY_TALK_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILLY_TALK_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_COMPLIMENT = new DialogueNode("Milly", "-", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
		//	UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_COMPLIMENT_BASE"));
		//	UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_COMPLIMENT"));
		//	UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_COMPLIMENT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return MILLY_TALK_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILLY_TALK_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_FLIRT = new DialogueNode("Milly", "-", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
	//		UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_FLIRT_BASE"));
	//		UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_FLIRT"));
	//		UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_FLIRT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return MILLY_TALK_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILLY_TALK_REPEAT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_HEAD_PAT = new DialogueNode("Milly", "-", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
		//	UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_HEAD_PAT_BASE"));
		//	UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_HEAD_PAT"));
		//	UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_HEAD_PAT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return MILLY_TALK_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILLY_TALK_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_KISS = new DialogueNode("Milly", "-", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
	//		UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_KISS_BASE"));
		//	UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_KISS"));
	//		UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_KISS_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return MILLY_TALK_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILLY_TALK_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_MAKE_OUT = new DialogueNode("Milly", "-", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
	//		UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_MAKE_OUT_BASE"));
	//		UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_MAKE_OUT"));
	//		UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_MAKE_OUT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return MILLY_TALK_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILLY_TALK_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode END_SEX = new DialogueNode("Finished", "Milly has had enough for now...", true) {

		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Milly.class))==0) {
				return "";
		//		return UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_END_SEX_NO_ORGASM");
			} else {
				return "";
		//		return UtilText.parseFromXMLFile("characters/nirth/milly", "MILLY_END_SEX");
			}
		}

		@Override
		public String getResponseTabTitle(int index) {
			return MILLY_TALK_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILLY_TALK_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_GIFT = new DialogueNode("Milly", "-", true, true) {

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return MILLY_TALK_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILLY_TALK_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HOMESTEAD_OFFER_HELP = new DialogueNode("Milly", "-", true) {

		@Override
		public String getContent() {
			return "You offer milly help with her tasks for the homestead.";
		//	return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_OFFER_HELP");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return MILLY_TALK_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILLY_TALK_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HOMESTEAD_REPORT_BACK = new DialogueNode("Nyan's Clothing Emporium", "-", true) {

		@Override
		public String getContent() {
			return "";
//			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_REPORT_BACK");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Leave", "Exit the store.", OFFICE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXIT_EMBARRASSED"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
};

