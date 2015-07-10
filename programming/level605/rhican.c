#include <iostream>
#include <string.h>
#include <stdio.h>

int main(int argc, char** argv)
{
    int min=10000;
    char tmparr[150];
    char *tmp=tmparr+50;

    for (int a=2; a<11; ++a)
        for (int b=a+1; b<50; ++b)
            for (int c=b+1; c<50; ++c)
                for (int d=c+1; d<50; ++d)
                    for (int e=d+1; e<50; ++e)
                    {
                        memset(tmparr,0,150);
                        tmp[1]=tmp[a]=tmp[b]=tmp[c]=tmp[d]=tmp[e]=1;

                        int totalcoins=6;
                        int stillemptyspots=93;
                        int numcoinsused=2;
                        do
                        {
                            int currentcoins=0;
                            for (int val=99; val; --val)
                                if((!tmp[val]) && (tmp[val]=tmp[val-1] | tmp[val-a] | tmp[val-b] |tmp[val-c] | tmp[val-d] | tmp[val-e])) ++currentcoins;

                            totalcoins+=(currentcoins*numcoinsused++);
                            stillemptyspots-=currentcoins;

                        } while ( totalcoins + stillemptyspots*numcoinsused < min &&
                                  ( stillemptyspots  || printf("found new best: 1:%i:%i:%i:%i:%i average: %f\n",a,b,c,d,e,(min=totalcoins)/99.0) ));

                    }
    return 0;
}

