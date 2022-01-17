package Redovalnica_Java;

class Drzava{
    public String Drzava_Slo;
    public String Drzava_Iso;
    public String DKratica;

    public Drzava(){

    }
    public Drzava(String drzava_slo){
        Drzava_Slo = drzava_slo;
    }
}
class Kraj extends Drzava{
    public String ImeK;
    public String Post_St;

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
}
public class Oseba extends Kraj{
    public String Ime;
    public String Priimek ;
    public char Spol ;
    public String Datum_R ;
    public String NaslovO ;
    public String EmailO ;

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
}
class Ucenec extends Oseba{
    public String URazred ;
    public String USola ;
    public String UcTelefon ;
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
}
class Ucitelj extends Oseba{
    public String UcSola ;
    public String Telefon ;
    public String SolskiEmail ;
    public String Geslo ;
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
}
class Solsko_Leto
{
    public String SLeto ;
    public Solsko_Leto()
    {

    }
    public Solsko_Leto(String solsko_leto)
    {
        SLeto = solsko_leto;
    }
}
class Razred extends Solsko_Leto
{
    public String ImeR ;
    public int St ;
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
}
class RazredPredmet extends Razred{
    public int Id_R_P_U ;
    public String ImeP ;
    public String KraticaP ;
    public String UciteljP;
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
}
class Ocena extends RazredPredmet{
    public String Ucenec ;
    public String UOcena ;
    public int StO ;
    public String DatumOcena ;
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
}
class Vrsta_Ur
{
    public String Ura ;
    public Vrsta_Ur()
    {

    }
    public Vrsta_Ur(String vrsta_ur)
    {
        Ura = vrsta_ur;
    }
}
class UreIzvedbe extends RazredPredmet{
    public int IdUr ;
    public String VrstaUre ;
    public String DatumCas ;
    public UreIzvedbe()
    {

    }
    public UreIzvedbe(int idUr)
    {
        IdUr = idUr;
    }

    public UreIzvedbe(String predmet, String razred, String vrstaure, String datumCas)
    {
        super(predmet, razred);
        VrstaUre = vrstaure;
        DatumCas = datumCas;
    }
    public UreIzvedbe(int id_razredi_predmeti, String vrsta_ure, String datumCas)
    {
        super(id_razredi_predmeti);
        VrstaUre = vrsta_ure;
        DatumCas = datumCas;
    }
    public UreIzvedbe(String predmet, String razred, String solsko_leto, String ucitelj, String vrstaure, String datumCas)
    {
        super(predmet, razred, solsko_leto, ucitelj);
        VrstaUre = vrstaure;
        DatumCas = datumCas;
    }
}
class Prisotnost extends UreIzvedbe{
    public String Ucenec ;
    public int Id_Ure_Izvedbe ;
    public String Opomba ;
    public Prisotnost()
    {

    }

    public Prisotnost(String ucenec,int id_ure_izvedbe, String opomba)
    {
        Ucenec = ucenec;
        Id_Ure_Izvedbe = id_ure_izvedbe;
        Opomba = opomba;
    }
}

