package com.infodesire.app;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.SystemUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

public class Main {

  private static Options options;

  public static void main( String... args ) throws IOException, ParseException, InterruptedException {

    print( "Demo 1.0" );
    print( "Running as " + SystemUtils.getUserName() + "@" + InetAddress.getLocalHost().getHostName() );
    print( "" );

    options = createOptions();

    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = parser.parse(options, args);

    List<String> argslist = cmd.getArgList();
    boolean lowercase = false;

    if( argslist.isEmpty() ) {
      showUsage( "No command given.", lowercase );
      Runtime.getRuntime().halt( 1 );
    }

    String command = argslist.get( 0 );


    if( cmd.hasOption( "l" ) ) {
      lowercase = Boolean.parseBoolean( cmd.getOptionValue( "l" ) );
    }

    if( command.equals( "help" ) ) {
      showUsage( "", lowercase );
    }

    Runtime.getRuntime().halt( 0 );

  }
  
  
  public static Options createOptions() {

    // create Options object
    Options options = new Options();

    // add l option for lowercase
    options.addOption( "l", false, "print lower case" );

    options.addOption(
      Option.builder()
        .argName( "lowercase" )
        .option( "l" )
        .hasArg()
        .desc( "print lower case" )
        .build()
    );

    return options;

  }


  private static void showUsage( String message, boolean lowercase ) {

    HelpFormatter formatter = new HelpFormatter();
    print( message );
    formatter.printHelp("hello [options] command", options);
    print( "" );
    print( "commands:" );
    print( "help \t show help" );

    if( lowercase ) {
      print( "option lowercase was set" );
    }

  }

  private static void print( String line ) {
    System.out.println( line );
  }

}