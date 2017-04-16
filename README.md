# StanfordTreeDrawer
A small tool to draw a only-text tree as the result of stanford coreNLP.
Welcome to use!

See Example.java to use.

##The tree we get like this:

    
                                                                ROOT                                                            
                                                                 |                                                             
                                                                 S                                                             
                                ╭╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶┬╶╶╶╶╶╶┬╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶┬╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╮  
                                S                              ,     NP                          VP                         .  
        ╭╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╮                          |     |        ╭╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╮                     |  
       NP                          VP                          ,    PRP      MD                      VP                     .  
       |       ╭╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╮                             |       |        ╭╶╶╶╶╶╶╶╶╶╶╶┬╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╮       
      PRP     VBZ                     NP                            you     can      VB          NP              PP       
       |       |             ╭╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╮                                   |        ╭╶╶╶╶╶╶╶╮       ╭╶╶╶╶╶╶╶╮   
      It      is            NP                  PP                                  use      DT      NN      IN      NP   
                      ╭╶╶╶╶╶╶╶╶╮           ╭╶╶╶╶╶╶╶╶╶╮                                       |        |       |       |   
                     DT       NNS         IN        NP                                      the     tool    like     DT   
                     |         |          |          |                                                                |   
                     a     sentences     for        NN                                                              that  
                                                    |     
                                                 example  
    
    
    
                                                    use/VB                                                
                        ╭╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶╶┬╶╶╶╶╶╶╶╶┬╶╶╶╶╶╶╶╶╶╶┬╶╶╶╶╶╶╶╶╶╶╶┬╶╶╶╶╶╶╶╶╶╶╶┬╶╶╶╶╶╶╶╶╶╮   
                     ccomp                   punct    nsubj       aux        dobj      nmod:like   punct 
                        |                       |        |          |          |            |         |   
                  sentences/NNS                ,/,    you/PRP    can/MD     tool/NN      that/DT     ./.  
         ╭╶╶╶╶╶╶╶╶╶╶┬╶╶╶╶╶╶╶╶╶┬╶╶╶╶╶╶╶╶╶╮                                      |            |     
       nsubj       cop       det    nmod:for                                  det         case    
         |          |         |         |                                      |           |     
      It/PRP     is/VBZ     a/DT   example/NN                               the/DT      like/IN  
                                        |    
                                      case   
                                        |    
                                     for/IN  



          ROOT      
            |       
           NP       
         ╭╶╶╶╶╶╶╶╮  
        NNS      .  
         |       |  
      Thanks     !  



    Thanks/NNS
         |    
        punct 
       |   
      !/.  




