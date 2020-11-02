# Voice-Control-for-FDM-3D-Printing
An android application that allows a user to interface with a 3D printer using their voice.

With three prior years of experience working and building 3D printers, it's evident that the 3D printing community could benefit from a new interface: one’s voice. Hence, this project embarks towards creating software that can effectively transfer voice commands to a 3D printer. 

AWS Cura Online GCODE Slicer: http://ec2-52-14-63-95.us-east-2.compute.amazonaws.com/
Requirements/Specifications: OctoPrint software must be pre-installed and successfully functioning on the user’s 3D printer. Custom Compute Server with Cura Engine CLI installed (refer to PHP and Bash Scripts in this repo). 

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
