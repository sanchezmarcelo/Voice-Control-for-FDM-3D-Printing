# Voice-Control-for-FDM-3D-Printing
Control your 3D Printer with your voice!

The rise of affordable and reliable fused deposition modeling (FDM) 3D printers has transformed small-scale manufacturing. Most consumer 3D printers are controlled through a built-in interface, typically an LCD screen with a rotary knob. Some also support third-party APIs or management solutions, offering remote control capabilities, model uploads, and performance tracking.

With three years of experience working with and building 3D printers, I saw an opportunity for a more intuitive interface: voice control.

This project aims to develop software that enables users to control a 3D printer using voice commands. These commands include mechanical operations such as heating the tool head, heating the build plate, and homing the axes, as well as initiating prints with phrases like, “Print a wrench.” Users interact with the system through an Android mobile app, which sends commands to Server A—responsible for direct communication with the 3D printer. For print requests, commands are forwarded to Server B, where 3D models are converted into G-code, the required format for printing.

Requirements/Specifications: OctoPrint software must be pre-installed and successfully functioning on the user’s 3D printer. Custom Compute Server with Cura Engine CLI installed (refer to PHP and Bash Scripts in this repo if you'd like to recreate). My custom server with an implementation of the Cura Engine Console C++ Application: http://ec2-52-14-63-95.us-east-2.compute.amazonaws.com/ 

HOWTO install the Cura Engine Console Verision: https://github.com/Ultimaker/CuraEngine

Feature A is inclusive of the following commands:

        Issue a Print Head Command
        
        Jog (moves the print head by a defined amount in one or more axes: X,Y, or Z)
        Ex. User states: “Jog Z-axis 50 mm” which “jogs”the 3D printer gantry 50 mm upwards on the Z axis.
        Other Jog Commands Include: “Jog X-axis 100 mm”, “Jog Y-axis -80 mm”
        
        Home 3D-Printer (moves the 3D printer’s tool head to [0, 0, 0] on all axis)
        Ex. User states: “Home printer” or “Home 3D-Printer”
        Homing takes place on a 3D printer before the execution of any print job
        
        Issue a Tool Command
        Nozzle (sets the nozzle temperature)
        Ex. User states: “Nozzle 200” to set the target temp of the print nozzle to 200 C
        
        Extrude (user can specify the mm of filament to be extruded from the nozzle) (print tool must be at a minimum of 180 C for PLA, and 220 for ABS in order to       perform the given operation)
        Ex. User states: “Extrude 30 mm”
        
        Retract (user can specify the mm of filament to be retracted from the nozzle) (print tool must be at a minimum of 180 C for PLA, and 220 for ABS in order to perform the given operation)
        Ex. User states: “Retract 10 mm”
        
        Issue a Bed Command
        Target (sets the print bed temperature)
        Ex. User states: “Bed 60”
                                                                                                                                                                                                                                       
Feature B:
        Example: User states the following print command. The application queries the results for 'wrench' (from thingiverse.com) and returns the results. You simply then select which 'wrench' object you'd like to print and hit the 'print this thing' button on the UI. The file is downloaded, unziped, and sliced into GCODE on the AWS server. The GCODE is then set to the OctoPrint API via curl. The printer begins to pre-heat and the object is queued for 3D printing! - All from simply stating so.
        
        “Print a Wrench”
