       Identification Division.
       Program-Id. AIDSYN.
      ****************************************************************
      * Test which AID key was pressed 'synchronously'
      * (that is, using conditional statements)
      ****************************************************************
       Data Division.
       Working-Storage Section.
           copy DFHAID.
       01  UserMessages.
           05  InitialMessage.
               10  filler pic x(25) value 'We invite you to press th'.
               10  filler pic x(25) value 'e Attention Identifier ke'.
               10  filler pic x(25) value 'y of your choice. Press E'.
               10  filler pic x(13) value 'nter to quit.'.
           05  FarewellMessage.
               10  filler pic x(19) value 'Thanks for playing!'.
           05  AckMessage pic x(80) value spaces.
           05  ThanksForPressingThe
                   pic x(24) value 'Thanks for pressing the '.
           05  KeyName    pic x(06) value spaces. 
           05  TheWordKey pic x(05) value ' key.'.
       01  MessageArea    pic x(100) value spaces.
       01  MessageLength  pic 9(04) binary value zero.
       01  AreWeDone      pic x value 'N'.
           88  WeAreDone        value 'Y'.
       Procedure Division.
           perform 1000-show-initial-message
           perform 2000-process-input
               until WeAreDone
           .
       1000-show-initial-message.
           move InitialMessage to MessageArea
           move length of InitialMessage to MessageLength
           perform 4000-send
           .
       2000-process-input.
           EXEC CICS RECEIVE END-EXEC
           evaluate EIBAID
               when DFHENTER
                    perform 3000-say-goodbye
               when DFHPA1
                    move 'PA1' to KeyName
                    perform 2500-give-thanks
               when DFHPA2
                    move 'PA2' to KeyName
                    perform 2500-give-thanks
               when DFHPA3
                    move 'PA3' to KeyName
                    perform 2500-give-thanks
               when DFHPF1
                    move 'PF1' to KeyName
                    perform 2500-give-thanks
               when DFHPF2
                    move 'PF2' to KeyName
                    perform 2500-give-thanks
               when DFHPF3
                    move 'PF3' to KeyName
                    perform 2500-give-thanks
               when DFHPF4
                    move 'PF4' to KeyName
                    perform 2500-give-thanks
               when DFHPF5
                    move 'PF5' to KeyName
                    perform 2500-give-thanks
               when DFHPF6
                    move 'PF6' to KeyName
                    perform 2500-give-thanks
               when DFHPF7
                    move 'PF7' to KeyName
                    perform 2500-give-thanks
               when DFHPF8
                    move 'PF8' to KeyName
                    perform 2500-give-thanks
               when DFHPF9
                    move 'PF9' to KeyName
                    perform 2500-give-thanks
               when DFHPF10
                    move 'PF10' to KeyName
                    perform 2500-give-thanks
               when DFHPF11
                    move 'PF11' to KeyName
                    perform 2500-give-thanks
               when DFHPF12
                    move 'PF12' to KeyName
                    perform 2500-give-thanks
               when DFHPF13
                    move 'PF13' to KeyName
                    perform 2500-give-thanks
               when DFHPF14
                    move 'PF14' to KeyName
                    perform 2500-give-thanks
               when DFHPF15
                    move 'PF15' to KeyName
                    perform 2500-give-thanks
               when DFHPF16
                    move 'PF16' to KeyName
                    perform 2500-give-thanks
               when DFHPF17
                    move 'PF17' to KeyName
                    perform 2500-give-thanks
               when DFHPF18
                    move 'PF18' to KeyName
                    perform 2500-give-thanks
               when DFHPF19
                    move 'PF19' to KeyName
                    perform 2500-give-thanks
               when DFHPF20
                    move 'PF20' to KeyName
                    perform 2500-give-thanks
               when DFHPF21
                    move 'PF21' to KeyName
                    perform 2500-give-thanks
               when DFHPF22
                    move 'PF22' to KeyName
                    perform 2500-give-thanks
               when DFHPF23
                    move 'PF23' to KeyName
                    perform 2500-give-thanks
               when DFHPF24
                    move 'PF24' to KeyName
                    perform 2500-give-thanks
               when other
                    perform 3000-say-goodbye
           end-evaluate
           .
       2500-give-thanks.
           move spaces to AckMessage
           move 1 to MessageLength
           string ThanksForPressingThe delimited by size
                  KeyName              delimited by space
                  TheWordKey           delimiated by size
              into AckMessage
              with pointer MessageLength
           move AckMessage to MessageArea
           perform 4000-send
           .
       3000-say-goodbye.
           set WeAreDone to true
           move FarewellMessage to MessageArea
           move length of FarewellMessage to MessageLength
           perform 4000-send
           EXEC CICS RETURN END-EXEC
           .
       4000-send.
           EXEC CICS SEND TEXT
               FROM(MessageArea)
               LENGTH(MessageLength)
               ERASE
           END-EXEC
           .                                    