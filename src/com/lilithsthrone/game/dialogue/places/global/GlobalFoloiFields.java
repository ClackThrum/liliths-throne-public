package com.lilithsthrone.game.dialogue.places.global;

import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.1
 * @version 0.3.2
 * @author Innoxia
 */
public class GlobalFoloiFields {
	
	public static final DialogueNode DOMINION_EXTERIOR = new DialogueNode("Dominion", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60 * 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/global/globalPlaces", "DOMINION_EXTERIOR");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Enter", "Head into Dominion.") {
					@Override
					public void effects() {
//						WorldType wt = ((AbstractGlobalPlaceType) Main.game.getPlayer().getGlobalCell().getPlace().getPlaceType()).getGlobalLinkedWorldType();
//						AbstractPlaceType pt = ((AbstractGlobalPlaceType) Main.game.getPlayer().getGlobalCell().getPlace().getPlaceType()).getGlobalLinkedWorldType().getEntryFromGlobalMapLocation();
						Main.game.getPlayer().setLocation(
								WorldType.DOMINION,
								PlaceType.DOMINION_PLAZA,//Main.game.getWorlds().get(wt).getCell(pt).getLocation(),
								false);
						
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					}
				};
			
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode FOLOI_FIELDS = new DialogueNode("Foloi Fields", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60 * 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/global/globalPlaces", "FOLOI_FIELDS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Farm work", "Approach one of the farms which is requesting help and see what it is you'd have to do in order to earn some flames.<br/>[style.italicsBad(Will be added soon!)]", null) {
					@Override
					public void effects() {
						//TODO generate world
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode FOLOI_FOREST = new DialogueNode("Foloi Forest", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2 * 60 * 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/global/globalPlaces", "FOLOI_FOREST");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Explore", "Take some time to explore this area of the forest and see what you can find.<br/>[style.italicsBad(Will be added soon!)]", null) {
					@Override
					public void effects() {
						//TODO generate world
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode GRASSLAND_WILDERNESS = new DialogueNode("Grassland Wilderness", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2 * 60 * 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/global/globalPlaces", "GRASSLAND_WILDERNESS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Explore", "Explore some of the valleys and see what you can find.<br/>[style.italicsBad(Will be added soon!)]", null) {
					@Override
					public void effects() {
						//TODO generate world
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode RIVER_HUBUR = new DialogueNode("River Hubur", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60 * 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/global/globalPlaces", "RIVER_HUBUR");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Explore", "Take some time to explore the shores of the river.<br/>[style.italicsBad(Will be added soon!)]", null) {
					@Override
					public void effects() {
						//TODO generate world
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ELIS = new DialogueNode("Elis", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60 * 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/global/globalPlaces", "ELIS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Approach", "Approach the walls of Elis, and head towards the main gatehouse.<br/>[style.italicsBad(Will be added soon!)]", null) {
					@Override
					public void effects() {
						//TODO
					}
				};

			} else {
				return null;
			}
		}
	};
	
	// MoreContent Modifications:
	public static final DialogueNode RIVER_HUBUR_WILD = new DialogueNode("River Hubur (Wild)", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60 * 60;
		}
		
		@Override
		public String getContent() {
			// TODO: RIVER_HUBUR_WILD XML
			return UtilText.parseFromXMLFile("places/global/globalPlaces", "RIVER_HUBUR");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Explore", "Take some time to explore the shores of the river.<br/>[style.italicsBad(Will be added soon!)]", null) {
					@Override
					public void effects() {
						//TODO generate world
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SHINRIN_HIGHLANDS = new DialogueNode("Shinrin highlands", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60 * 60;
		}
		
		@Override
		public String getContent() {
			// TODO: SHINRIN_HIGHLANDS XML
			return "";
			//return UtilText.parseFromXMLFile("places/global/globalPlaces", "SHINRIN_HIGHLANDS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Explore", "Take some time to explore the highlands.<br/>[style.italicsBad(Will be added soon!)]", null) {
					@Override
					public void effects() {
						//TODO generate world
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MOUNTAINS = new DialogueNode("Mountains", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60 * 60;
		}
		
		@Override
		public String getContent() {
			// TODO: MOUNTAINS XML
			return "";
			//return UtilText.parseFromXMLFile("places/global/globalPlaces", "SHINRIN_HIGHLANDS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Explore", "Take some time to explore the mountainside.<br/>[style.italicsBad(Will be added soon!)]", null) {
					@Override
					public void effects() {
						//TODO generate world
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode NIRTH_EXTERIOR = new DialogueNode("Nirth", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60 * 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/nirth/nirthGlobal", "NIRTH_EXTERIOR");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Enter", "Head into Nirth.") {
					@Override
					public void effects() {
//						WorldType wt = ((AbstractGlobalPlaceType) Main.game.getPlayer().getGlobalCell().getPlace().getPlaceType()).getGlobalLinkedWorldType();
//						AbstractPlaceType pt = ((AbstractGlobalPlaceType) Main.game.getPlayer().getGlobalCell().getPlace().getPlaceType()).getGlobalLinkedWorldType().getEntryFromGlobalMapLocation();
						Main.game.getPlayer().setLocation(
								WorldType.NIRTH,
								PlaceType.NIRTH_PLAZA,//Main.game.getWorlds().get(wt).getCell(pt).getLocation(),
								false);
						
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					}
				};
			
			} else {
				return null;
			}
		}
	};
}
