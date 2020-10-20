# QuizMaxter
QuizMaxter 3000 is a learning project using Java and JavaFX to build an application to support the use for real-time quiz management.

TODO:
- ~~Lägg in ljudbläddrare för rätt och fel ljud: CHECK~~
- ~~Ta bort funktionen att lägga ljud till frågor: CHECK~~
- ~~Flytta filechooser till egen klass (så det går att ladda och spara frågorna): CHECK~~
- ~~När en ny fråga läggs till, ska den hamna efter den markerade frågan.: CHECK~~
- ~~Lägg till uppdateraknapp som uppdaterar den markerade frågan, med den nya datan i fälten: CHECK~~
- ~~Lägg till hjälpavsnitt som berättar om hur man använder programmet: CHECK~~
- ~~Lägg till en återställknapp som sätter allt som om programmet startat på nytt (kanske under Arkiv?): CHECK~~
- ~~Se till så frågearrayen töms när den ska, på rätt sätt: CHECK~~
- ~~Lägg till bekräftelseruta när man startar nytt spel/resettar: CHECK~~
- ~~Fixa så NY GREN inte blir Fråga 0: CHECK~~
- ~~Sätta pointvalue till 1 som default: CHECK~~
- ~~Om man avbryter svarad fråga ska ingenting hända: CHECK~~
- ~~Spara och ladda ljud presets: CHECK~~
- ~~Lägg till knapp för att låsa upp låsta spelare: CHECK~~
- ~~Ta bort dialogruta vid uppdaterad fråga, lägg till en mer subtil visuell bekräftelse: CHECK~~
- ~~Lägg till "Tiden ute"-knapp: CHECK~~
- ~~Fixa automatisk radbrytning i textlådan: CHECK~~

VIKTIGT:
- Inaktivera spelarnas möjlighet att svara när NY GREN är markerad.

MINDRE VIKTIGT:
- Fixa hotkeys till rätt/fel svar.
- Lägga till knapp för att låsa/låsa upp buzzerknapparna?
- Refaktorisera och städa

Ex. på hjälptext:

Redigeringsläge:
För att lägga till en fråga, fyll i frågan i den stora textrutan, skriv in ett poängvärde och välj om det ska vara straffpoäng eller inte.
Får du upp en felruta när du skriver in poäng så håll in skift, tryck på pilarna på tangentbordet så allt blir markerat och byt sen ut mot rätt siffra (här var det dålig programmering).
Tryck på [+] till höger för att lägga till frågan i listan.

När en fråga skapas tilldelas den ett ID med ett nummer som motsvarar antalet frågor + 1.

För att lägga till en fråga mitt i listan markerar du den fråga som den ska hamna efter. Frågan kommer dock få det högsta ID:t
(ex: om du har en lista med 5 frågor och markerar fråga 2, så kommer fråga 6 hamna på fråga 3:s plats, osv. Siffrorna är alltså lite CP ännu.)

För att uppdatera en fråga, välj den i listan, ändra till de värdena du vill ha och klicka sedan på 'Uppdatera'. Voila.

För att spara en frågelista till ett annat tillfälle, tryck på spara. Välj var du vill spara, ge filen ett namn och tryck på spara.
Denna lista kan du sedan ladda när du vill och vips har du alla frågor i programmet igen (gamla frågor försvinner när du laddar en ny lista).


För att byta namn på spelare, se till att vara i redigeringsläge, skriv in namnet du vill ha i motsvarande ruta och tryck på Enter efter redigering,
en popup med det nya namnet ska dyka upp.


Spelläge:
För att byta till spelläge, klicka på arkiv och sen växla.
Nu kan du inte längre ändra på saker och ting, och spelarna har läge att trigga sina buzzers. Svarar de fel så låses spelaren och dess poängruta blir gul.
Den fråga som är vald i listan till höger, är den som gäller. Du kan antingen välja gällande fråga genom att klicka på den du vill ha eller gå vidare mha. 'W' eller 'S'.
Svarar en spelare rätt på en fråga hoppar den till nästa fråga i listan.

(Jag kom på nu att jag kanske skulle lägga in en knapp som gör det så att spelarna kan svara först efter att du trycker på knappen? Eller så slutar du bara läsa frågan när nån trycker, så torskar de sin chans.)

För att låsa upp alla blockerade (de gula) spelarna, tryck på 5. Spelarna har nu fritt fram att trycka på sina buzzers igen.
