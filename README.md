#A server emulator for Maplestory.


Original set up guide is in their discord server https://discord.gg/DyFcAM8
Source Tree - https://www.sourcetreeapp.com/
IntelliJ (Community version) - https://www.jetbrains.com/idea/download/#section=windows
MySQL Workbench - https://dev.mysql.com/downloads/workbench/
WAMP Server - http://www.wampserver.com/en/
Java Dev Kit 1.8u172 - http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html


MapleStory Setup v176
http://download2.nexon.net/Game/MapleStory/FullVersion/176/MSSetupv176.exe
http://download2.nexon.net/Game/MapleStory/FullVersion/176/MSSetupv176.z01
http://download2.nexon.net/Game/MapleStory/FullVersion/176/MSSetupv176.z02
http://download2.nexon.net/Game/MapleStory/FullVersion/176/MSSetupv176.z03
http://download2.nexon.net/Game/MapleStory/FullVersion/176/MSSetupv176.z04
http://download2.nexon.net/Game/MapleStory/FullVersion/176/MSSetupv176.z05
http://download2.nexon.net/Game/MapleStory/FullVersion/176/MSSetupv176.z06


Wz IDs - https://mega.nz/#!FPhyzDqb!Qbjn4QHD1lF8A473tGagrUNKIFIOXDcQbvYbxHoWQ1Y
WzDumper - https://mega.nz/#!dSInlaBR!EdlI9i5bKB66TfAgFOdnLFeU4CdgFWhj-YgMoJC_5Ek
Dumping
Open up your Source Folder, and create 2 new folders  'wz' and 'dat' , leave them empty

Once you've finished installing the MSSetup v176
Open up WZ Dumper

Click on Dump All WZ Files from Folder
>Select the folder where your MSSetup is installed in, then select the wz folder you've created in the Source Folder.
This dumping process may take a bit. whilst this is dumping, you can start on the other steps
.






3. Creating the Database
Run WAMP server, and make sure it is lit green

Open MySQL, Add a new connection

Name the connection w/e, preferably swordie just so you remember

No need to touch any other options, click Ok.
Now Click on the connection:

On the top left, create a new Schema in the connection

Name this Schema   swordie   (needs to be 'swordie', nothing else, nothing capitalised)
No need to touch any other options, just click Apply and Finish

Now, Open a script file

Select the InitTables_characters.sql file in  the  SourceFolder >> sql
Double click the swordie schema so it becomes bold

Then Execute the file

don't worry if the script file is different from what the picture above shows,  we add stuff over time so they will differ slightly
anyhow

Once that's done, the database will be finished. and you'll have an account: 
ID - admin
Pass - admin
PIC - 111 111


Now that InitTables_characters is done, you will have to to do same for all the other script files in the sql folder
.






4. Running the server  [Note] You cannot run the server before Step 2 (Dumping the WzFiles) is completely finished.
Open up IntelliJ

you want to click on   Open project and select the source folder
once you've opened it, you should see a project window on the left side of your screen.
it should look something like this:

open up the folder 'src' and it's subfolders till you find Server

if you notice that the folder 'java' isn't marked blue on your end.
mark it as 'Sources Root'
by right clicking the 'java' folder and hovering over  Mark Directory as
then selecting Sources Root

 

If everything went correctly, you should be able to run the server now

.

If you get prompts about selecting a SDK
click on 'configure SDK'  (Ctrl + Shift + Alt + S)

then select the jdk 1.8 you've installed

and select apply
.

if you get some pop-up about a Unconfigured or Missing Git
you want to click on 'download git' and Download the git from the website that should pop-up


once you've downloaded that,
press Ctrl + Alt + S  to open the Settings menu

Under Version Control -> Git

You want to select the git path

and select apply
.


When running the server for the first time. The server will generate all the dat files for you. (this may take quite a while)
As long as you don't see any red errors you should be fine
Once you see

The server is done loading.
If it's the first time, please restart the server

Now you should be good to go!
.

Run SwordieMS.exe without the GK Bypass first, and after running it once you have to add the GK Bypass to the folder
https://www.gamekiller.net/threads/gkbypass-public-maplestory-bypass-with-multi-client-last-updated-12-23-2018.3250371/
is the bypass ^

download link is in the thread, but as some may not have a GK account here ya go:
https://hostr.co/jKid8thaqfru
Password = gk4life

Now when you launch the SwordieMS.exe in your MSSetupv176 folder, you'll get a prompt asking you to login with your GK account
just use:
ID: bizarrol18
pass: gamerzhacking
If that account doesnt work, just create your own GK account.

That should be everything.
