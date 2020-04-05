package com.marcelo.android.voiceactivated3dprinter;

import retrofit2.Call;

public class Print implements PrintJobClient {

    public class files{
        files(String name, String path, String type, String[] typePath, String origin, String resource, String download){
            local l = new local(name, path, type, typePath, origin, resource, download);
        }
    }

    public class local{
        String name;
        String path;
        String type;
        String[] typePath;
        String origin;

        local(String n, String p, String t, String[]a, String o, String resources, String download ){
            n = name;
            p = path;
            t = type;
            a = typePath;
            o = origin;
            refs r = new refs(resources, download);
        }

        public class refs{
            String resource;
            String download;

            refs(String r, String d){
                r = resource;
                d = download;
            }
        }
    }



    Print(String name, String path, String type, String[] typePath, String origin, String resource, String download){
        files file = new files(name, path, type, typePath, origin, resource, download);
    }

    @Override
    public Call<Print> newPrint(Print print) {
        return null;
    }

}
