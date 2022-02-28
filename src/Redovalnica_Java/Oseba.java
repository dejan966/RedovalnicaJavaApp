package Redovalnica_Java;

class Drzava{
    private String Drzava_Slo;
    public String getDrzavaSlo(){return Drzava_Slo; }
    private String Drzava_Iso;
    private String DKratica;

    public Drzava(){

    }
    public Drzava(String drzava_slo){
        Drzava_Slo = drzava_slo;
    }
    public String toString(){
        return "Država: " + getDrzavaSlo();
    }
}
class Kraj extends Drzava{
    private String ImeK;
    public String getImeK(){return ImeK;}
    private String Post_St;

    public Kraj(){

    }
    public Kraj(String imek)
    {
        ImeK = imek;
    }
    public Kraj(String imek, String imed)
    {
        super(imed);
        ImeK = imek;
    }
    public String _toString(){
        return "Kraj: " + getImeK() + "\nDržava: " + super.getDrzavaSlo();
    }
}
public class Oseba extends Kraj{
    private String Ime;
    public String getIme(){return Ime;}
    private String Priimek ;
    public String getPriimek(){return Priimek;}
    public char Spol ;
    public char getSpol(){return Spol;}
    private String Datum_R ;
    public String getDatum_R(){return Datum_R;}
    private String NaslovO ;
    public String getNaslovO(){return NaslovO;}
    private String EmailO ;
    public String getEmailO(){return EmailO;}

    public Oseba()
    {

    }
    public Oseba(String ime, String priimek)
    {
        Ime = ime;
        Priimek = priimek;
    }
    public Oseba(String ime, String priimek, char spol, String datum_r, String naslov, String email, String kraj)
    {
        super(kraj);
        Ime = ime;
        Priimek = priimek;
        Spol = spol;
        Datum_R = datum_r;
        NaslovO = naslov;
        EmailO = email;
    }
    public String _toString(){
        return "\nIme: " + getIme() + "\nPriimek: " + getPriimek() + "\nSpol: " + getSpol() + "\nDatum rojstva: " + getDatum_R() + "\nNaslov: " + getNaslovO() + "\nEmail: " + getEmailO() + "\nKraj: " + super.getImeK();
    }
}
class Ucenec extends Oseba{
    private String URazred ;
    public String getURazred(){return URazred;}
    private String USola ;
    public String getUSola(){return USola;}
    private String UcTelefon ;
    public String getUcTelefon(){return UcTelefon;}
    public Ucenec()
    {

    }
    public Ucenec(String ime, String priimek)
    {
        super(ime, priimek);
    }
    public Ucenec(String ime, String priimek, char spol, String datum_r, String naslov, String email, String kraj)
    {
        super(ime, priimek, spol, datum_r, naslov, email, kraj);
    }
    public Ucenec(String ime, String priimek, char spol, String datum_r, String naslov, String email, String kraj, String razred, String imeSola)
    {
        super(ime, priimek, spol, datum_r, naslov, email, kraj);
        USola = imeSola;
        URazred = razred;
    }
    public String _toString(){
        return "\nIme: " + getIme() + "\nPriimek: " + getPriimek() + "\nSpol: " + getSpol() + "\nDatum rojstva: " + getDatum_R() + "\nNaslov: " + getNaslovO() + "\nEmail: " + getEmailO() + "\nKraj: " + super.getImeK() + "\nRazred: " + getURazred() + "\nŠola: " + getUSola();
    }
}
class Ucitelj extends Oseba{
    private String UcSola ;
    public String getUcSola(){return UcSola;}
    private String Telefon ;
    public String getTelefon(){return Telefon;}
    private String SolskiEmail ;
    public String getSolskiEmail(){return SolskiEmail;}
    private String Geslo ;
    public String getGeslo(){return Geslo;}
    public Ucitelj()
    {

    }
    public Ucitelj(String email)
    {
        SolskiEmail = email;
    }
    public Ucitelj(String sEmail, String geslo)
    {
        SolskiEmail = sEmail;
        Geslo = geslo;
    }
    public Ucitelj(String ime, String priimek, char spol, String datum_r, String naslov, String email, String kraj)
    {
        super(ime, priimek, spol, datum_r, naslov, email, kraj);
    }
    public Ucitelj(String ime, String priimek, char spol, String datum_r, String naslov, String email,String kraj, String imeSola, String telefon, String solskiEmail)
    {
        super(ime, priimek, spol, datum_r, naslov, email, kraj);
        UcSola = imeSola;
        Telefon = telefon;
        SolskiEmail = solskiEmail;
    }
    public String getIme(){
        return "\nIme: " + getIme() + "\nPriimek: " + getPriimek() + "\nSpol: " + getSpol() + "\nDatum rojstva: " + getDatum_R() + "\nNaslov: " + getNaslovO() + "\nEmail: " + getEmailO() + "\nKraj: " + super.getImeK() + "\nŠola: " + getUcSola() + "\nTelefon: " + getTelefon() + "\nKontakt: " + getSolskiEmail();

    }
}
class Solsko_Leto
{
    private String SLeto ;
    public String getSLeto(){return SLeto;}
    public Solsko_Leto()
    {

    }
    public Solsko_Leto(String solsko_leto)
    {
        SLeto = solsko_leto;
    }
    public String _toString(){
        return "Šolsko leto: " + getSLeto();
    }
}
class Razred extends Solsko_Leto
{
    private String ImeR ;
    public String getImeR(){return ImeR;}
    public int St ;
    public int getSt(){return St;}
    public Razred()
    {

    }
    public Razred(String imeR)
    {
        ImeR = imeR;
    }
    public Razred(String imeR, String s_leto)
    {
        super(s_leto);
        ImeR = imeR;
    }
    public String _toString(){
        return "Razred: " + getImeR() + "\nŠolsko leto: " + super.getSLeto();
    }
}
class RazredPredmet extends Razred{
    public int Id_R_P_U ;
    public int getId_R_P_U(){return Id_R_P_U;}
    private String ImeP ;
    public String getImeP(){return ImeP;}
    private String KraticaP ;
    public String getKraticaP(){return KraticaP;}
    private String UciteljP;
    public String getUciteljP(){return UciteljP;}
    public RazredPredmet()
    {

    }
    public RazredPredmet(int id_p_r_u)
    {
        Id_R_P_U = id_p_r_u;
    }
    public RazredPredmet(String imeP)
    {
        ImeP = imeP;
    }
    public RazredPredmet(String imeP, String imeR)
    {
        super(imeR);
        ImeP = imeP;
    }
    public RazredPredmet(String imeP, String imeR, String sLeto)
    {
        super(imeR, sLeto);
        ImeP = imeP;
    }
    public RazredPredmet(String predmet, String razred, String ucitelj, String solsko_leto)
    {
        super(razred, solsko_leto);
        ImeP = predmet;
        UciteljP = ucitelj;
    }
    public String _toString(){
        return "Predmet: " + getImeP() + "\nRazred: " + super.getImeR() + "\nUčitelj: " + getUciteljP() + "\nŠolsko leto: " + super.getSLeto();
    }
}
class Ocena extends RazredPredmet{
    private String Ucenec ;
    public String getUcenec(){return Ucenec;}
    private String UOcena ;
    public String getUOcena(){return UOcena;}
    private int StO ;
    public int getStO(){return StO;}
    private String DatumOcena ;
    public String getDatumOcena(){return DatumOcena;}
    public Ocena()
    {

    }
    public Ocena(int stO)
    {
        StO = stO;
    }
    public Ocena(String ucenec, int stO)
    {
        Ucenec = ucenec;
        StO = stO;
    }
    public Ocena(String ucenec, String uOcena, String datum, int id_p_r_u)
    {
        super(id_p_r_u);
        Ucenec = ucenec;
        UOcena = uOcena;
        DatumOcena = datum;
    }
    public Ocena(String ucenec, String uOcena, String datum, String predmet, String razred, String solsko_leto, String ucitelj)
    {
        super(predmet, razred, ucitelj, solsko_leto);
        Ucenec = ucenec;
        UOcena = uOcena;
        DatumOcena = datum;
    }
    public String _toString(){
        return "Učenec: " + getUcenec() + "Ocena: " + getUOcena() + "Datum ocene: " + getDatumOcena() + "\nPredmet: " + getImeP() + "\nRazred: " + super.getImeR() + "\nUčitelj: " + getUciteljP() + "\nŠolsko leto: " + super.getSLeto();
    }
}
class Vrsta_Ur
{
    private String Ura ;
    public String getUra(){return Ura;}
    public Vrsta_Ur()
    {

    }
    public Vrsta_Ur(String vrsta_ur)
    {
        Ura = vrsta_ur;
    }
    public String _toString(){
        return "Vrsta ure: " + getUra();
    }
}
class UreIzvedbe extends RazredPredmet{
    public int IdUr ;
    public int getIdUr(){return IdUr;}

    private String VrstaUre ;
    public String getVrstaUre(){return VrstaUre;}

    private String DatumCas ;
    public String getDatumCas(){return DatumCas;}

    public String Datum;
    public String getDatum(){return Datum;}
    public UreIzvedbe()
    {

    }
    public UreIzvedbe(int idUr)
    {
        IdUr = idUr;
    }

    public UreIzvedbe(String predmet, String razred, String sLeto, String vrstaure, String datumCas)
    {
        super(predmet, razred, sLeto);
        VrstaUre = vrstaure;
        DatumCas = datumCas;

    }
    public UreIzvedbe(int id_razredi_predmeti, String vrsta_ure, String datumCas, String datum)
    {
        super(id_razredi_predmeti);
        VrstaUre = vrsta_ure;
        DatumCas = datumCas;
        Datum = datum;
    }
    public UreIzvedbe(String predmet, String razred, String solsko_leto, String ucitelj, String vrstaure, String datumCas)
    {
        super(predmet, razred, solsko_leto, ucitelj);
        VrstaUre = vrstaure;
        DatumCas = datumCas;
    }
    public String _toString(){
        return "Predmet: " + getImeP() + "\nRazred: " + super.getImeR() + "\nUčitelj: " + getUciteljP() + "\nŠolsko leto: " + super.getSLeto() + "\nVrsta ure: " + getVrstaUre() + "\nDatum: " + getDatumCas();
    }
}
class Prisotnost extends UreIzvedbe{
    private String UcenecN ;
    public String getUcenecN(){return UcenecN;}
    private String Opomba ;
    public String getOpomba(){return Opomba;}
    public Prisotnost()
    {

    }
    public Prisotnost(String predmet, String razred, String solsko_leto, String vrsta_ure, String datum)
    {
        super(predmet, razred, solsko_leto, vrsta_ure, datum);
    }
    public Prisotnost(String ucenec,int id_ure_izvedbe, String opomba)
    {
        super(id_ure_izvedbe);
        UcenecN = ucenec;
        Opomba = opomba;
    }
    public String _toString(){
        return "Učenec: " + getUcenecN() + "\nId ure izvedbe: " + super.getIdUr() + "\nOpomba: " + getOpomba();
    }
}