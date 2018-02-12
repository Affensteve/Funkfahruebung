package kom.feuerwehr.domain;

import kom.feuerwehr.core.Hersteller;
import kom.feuerwehr.core.Klassifizierung;
import kom.feuerwehr.core.Standort;

public class Fahrzeug {

   private String funkrufname;
   private Standort standort;
   private String kennzeichen;
   private Klassifizierung klassifizierung;
   private String baujahr;
   private Hersteller hersteller;
   private String erstzulassung;
   
   public void Fahrzeuge (String rufname) {
      this.funkrufname = rufname;
   }
   
   public Fahrzeug( String funkrufname, Standort standort, String kennzeichen, Klassifizierung klassifizierung, String baujahr, Hersteller hersteller,
         String erstzulassung ) {
      this.funkrufname = funkrufname;
      this.standort = standort;
      this.kennzeichen = kennzeichen;
      this.klassifizierung = klassifizierung;
      this.baujahr = baujahr;
      this.hersteller = hersteller;
      this.erstzulassung = erstzulassung;
   }

   public String getFunkrufname( ) {
      return funkrufname;
   }
   public void setFunkrufname( String funkrufname ) {
      this.funkrufname = funkrufname;
   }
   public Standort getStandort( ) {
      return standort;
   }
   public void setStandort( Standort standort ) {
      this.standort = standort;
   }
   public String getKennzeichen( ) {
      return kennzeichen;
   }
   public void setKennzeichen( String kennzeichen ) {
      this.kennzeichen = kennzeichen;
   }
   public Klassifizierung getKlassifizierung( ) {
      return klassifizierung;
   }
   public void setKlassifizierung( Klassifizierung klassifizierung ) {
      this.klassifizierung = klassifizierung;
   }
   public String getBaujahr( ) {
      return baujahr;
   }
   public void setBaujahr( String baujahr ) {
      this.baujahr = baujahr;
   }
   public Hersteller getHersteller( ) {
      return hersteller;
   }
   public void setHersteller( Hersteller hersteller ) {
      this.hersteller = hersteller;
   }
   public String getErstzulassung( ) {
      return erstzulassung;
   }
   public void setErstzulassung( String erstzulassung ) {
      this.erstzulassung = erstzulassung;
   }
}
