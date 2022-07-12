package qaautomation.utils;

public class CleartripEnums {

	public enum clearTripOptns {

		FLIGHT("Flights"), HOTELS("Hotels"),CLEARTRIP("Cleartrip for Work"),TOP_OFFERS("Top Offers");

		private String displayName;

		clearTripOptns(String displayName) {
			this.displayName = displayName;
		}

		public String getName() {
			return this.displayName;
		}

	}

}