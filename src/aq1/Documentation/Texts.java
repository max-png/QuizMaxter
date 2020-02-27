package aq1.Documentation;

public class Texts {

    public static String guideText(){
        return "Redigeringsläge:\n" +
                "För att lägga till en fråga, fyll i frågan i den stora textrutan, skriv in ett poängvärde och välj om det ska vara straffpoäng eller inte.\n" +
                "Får du upp en felruta när du skriver in poäng så håll in skift, tryck på pilarna på tangentbordet så allt blir markerat och byt sen ut mot rätt siffra (här var det dålig programmering).\n" +
                "Tryck på [+] till höger för att lägga till frågan i listan.\n" +
                "\n" +
                "När en fråga skapas tilldelas den ett ID med ett nummer som motsvarar antalet frågor + 1.\n" +
                "\n" +
                "För att lägga till en fråga mitt i listan markerar du den fråga som den ska hamna efter. Frågan kommer dock få det högsta ID:t\n" +
                "(ex: om du har en lista med 5 frågor och markerar fråga 2, så kommer fråga 6 hamna på fråga 3:s plats, osv. Siffrorna är alltså lite CP ännu.)\n" +
                "\n" +
                "För att uppdatera en fråga, välj den i listan, ändra till de värdena du vill ha och klicka sedan på 'Uppdatera'. Voila.\n" +
                "\n" +
                "För att spara en frågelista till ett annat tillfälle, tryck på spara. Välj var du vill spara, ge filen ett namn och tryck på spara.\n" +
                "Denna lista kan du sedan ladda när du vill och vips har du alla frågor i programmet igen (gamla frågor försvinner när du laddar en ny lista).\n" +
                "\n" +
                "\n" +
                "För att byta namn på spelare, se till att vara i redigeringsläge, skriv in namnet du vill ha i motsvarande ruta och tryck på Enter efter redigering,\n" +
                "en popup med det nya namnet ska dyka upp.\n" +
                "\n" +
                "\n" +
                "Spelläge:\n" +
                "För att byta till spelläge, klicka på arkiv och sen växla.\n" +
                "Nu kan du inte längre ändra på saker och ting, och spelarna har läge att trigga sina buzzers. Svarar de fel så låses spelaren och dess poängruta blir gul.\n" +
                "Den fråga som är vald i listan till höger, är den som gäller. Du kan antingen välja gällande fråga genom att klicka på den du vill ha eller gå vidare mha. 'W' eller 'S'.\n" +
                "Svarar en spelare rätt på en fråga hoppar den till nästa fråga i listan.\n" +
                "\n" +
                "(Jag kom på nu att jag kanske skulle lägga in en knapp som gör det så att spelarna kan svara först efter att du trycker på knappen? Eller så slutar du bara läsa frågan när nån trycker, så torskar de sin chans.)\n" +
                "\n" +
                "För att låsa upp alla blockerade (de gula) spelarna, tryck på 5. Spelarna har nu fritt fram att trycka på sina buzzers igen.";
    }

}
