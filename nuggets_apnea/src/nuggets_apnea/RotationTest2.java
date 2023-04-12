/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nuggets_apnea;

import java.util.ArrayList;

/**
 *
 * @author seabirds
 */
public class RotationTest2 
{
     double del;
    CNFrame cn;
    MainFrame1 mf;
    int j=0;
    RotationTest2(MainFrame1 me,CNFrame n1, double g2,int jj)
    {
        mf=me;
        cn=n1;        
        del=g2;
        j=jj;
    }
    
    public double  FindScore()
    {
        double score1=0;
        try
        {
             ArrayList arrList=new ArrayList();
             String input[]=new String[mf.val.length];
             for(int k=0;k<mf.val.length;k++)
             {
                String g1="";
                for(int j1=0;j1<mf.val[0].length;j1++)
                {
                    g1=g1+mf.val[k][j1]+" ";
                }
                input[k]=g1.trim();
            }
        
             
            for(int i=10;i<360;i+=10)
            {
                ArrayList TempArray=new ArrayList();
                for(int k=0;k<mf.val[0].length;k++)
                {
                    if(k!=j)
                    {
                        double delX=del*Math.cos((Math.PI/180)*i);
                        double delY=del*Math.sin((Math.PI/180)*i);
                        String Vj1[]=new String[cn.NV.length];
                        String Vk1[]=new String[cn.NV.length];
                        
                        String Vj2[]=new String[cn.NV.length];
                        String Vk2[]=new String[cn.NV.length];
                        
                        for(int t=0;t<cn.NV.length;t++)
                        {
                            Vj1[t]=String.valueOf(delX+Double.parseDouble(cn.NV[t][j]));
                            Vj2[t]=String.valueOf(Double.parseDouble(cn.NV[t][j])-delX);
                            //ns.nf.Nval1[j][t]=String.valueOf(delX+Double.parseDouble(ns.nf.Nval[j][t]));
                        }
                        for(int t=0;t<cn.NV.length;t++)
                        {
                            Vk1[t]=String.valueOf(delY+Double.parseDouble(cn.NV[t][j]));
                            Vk2[t]=String.valueOf(Double.parseDouble(cn.NV[t][j])-delY);
                            //ns.nf.Nval1[k][t]=String.valueOf(delY+Double.parseDouble(ns.nf.Nval[k][t]));
                        }
                        String Nval1[][]=new String[cn.NV.length][cn.NV[0].length-1];
                        String Nval2[][]=new String[cn.NV.length][cn.NV[0].length-1];
                        if(j<k)
                        {
                            for(int a1=0;a1<cn.NV.length;a1++)
                            {
                                for(int a2=0;a2<j-1;a2++)
                                {
                                    Nval1[a1][a2]=cn.NV[a1][a2];
                                    Nval2[a1][a2]=cn.NV[a1][a2];
                                }   
                                Nval1[a1][j]=Vj1[a1];                           
                                Nval2[a1][j]=Vj2[a1];    
                            }
                            for(int a1=0;a1<cn.NV.length;a1++)
                            {
                                for(int a2=j+1;a2<k-1;a2++)
                                {
                                    Nval1[a1][a2]=cn.NV[a1][a2];
                                    Nval2[a1][a2]=cn.NV[a1][a2];
                                }
                                Nval1[a1][k]=Vk1[a1];                            
                                Nval2[a1][k]=Vk2[a1];                            
                            }
                            for(int a1=0;a1<cn.NV.length;a1++)
                            {
                                for(int a2=k;a2<cn.NV[0].length;a2++)
                                {
                                    Nval1[a1][a2]=cn.NV[a1][a2];
                                    Nval2[a1][a2]=cn.NV[a1][a2];
                                }                                        
                            }
                        
                        } //j<k if
                        else
                        {
                            for(int a1=0;a1<cn.NV.length;a1++)
                            {
                                for(int a2=0;a2<k-1;a2++)
                                {
                                    Nval1[a1][a2]=cn.NV[a1][a2];
                                    Nval2[a1][a2]=cn.NV[a1][a2];
                                }
                                Nval1[a1][k]=Vk1[a1];                            
                                Nval2[a1][k]=Vk2[a1];                            
                            }
                            for(int a1=0;a1<cn.NV.length;a1++)
                            {
                                for(int a2=k+1;a2<j-1;a2++)
                                {
                                    Nval1[a1][a2]=cn.NV[a1][a2];
                                    Nval2[a1][a2]=cn.NV[a1][a2];
                                }
                                Nval1[a1][j]=Vk1[a1];                            
                                Nval2[a1][j]=Vk2[a1];  
                            }
                            for(int a1=0;a1<cn.NV.length;a1++)
                            {
                                for(int a2=j+1;a2<cn.NV[0].length-1;a2++)
                                {
                                    Nval1[a1][a2]=cn.NV[a1][a2];
                                    Nval2[a1][a2]=cn.NV[a1][a2];
                                }                                        
                            }
                        } //else
                    
                        
                        String test1[]=new String[Nval1.length];
                        String test2[]=new String[Nval2.length];
                        
                        for(int k1=0;k1<Nval1.length;k1++)
                        {
                            String g1="";
                            String g2="";
                            for(int j1=0;j1<Nval1[0].length;j1++)
                            {
                                
                                g1=g1+Nval1[k1][j1]+" ";
                                g2=g2+Nval2[k1][j1]+" ";
                            }
                            test1[k1]=g1.trim();
                            test2[k1]=g2.trim();
                            
                        }
                        
                    
                        DataClassification1 dc1=new DataClassification1(input,mf.cls,test1);
                        ArrayList at1=dc1.Testclassifier();
                        int wjP=0;
                        for(int k1=0;k1<at1.size();k1++)
                        {
                            String sg1=at1.get(k1).toString();
                            if(!(cn.nf.centerCls.equals(sg1)))
                            {
                                wjP++;
                            }
                        }   
                    
                        double wjP1=(double)wjP/(double)at1.size();
                        //System.out.println("----- wjP "+wjP);
                        //wjPList1.add(wjP1);
                        DataClassification1 dc2=new DataClassification1(input,mf.cls,test2);
                        ArrayList at2=dc2.Testclassifier();
                        int wjM=0;
                        for(int k1=0;k1<at2.size();k1++)
                        {
                            String sg1=at2.get(k1).toString();
                            if(!(cn.nf.centerCls.equals(sg1)))
                            {
                               wjM++;
                            }
                        }
                
                        double wjM1=(double)wjM/(double)at2.size();
                        //wjPList2.add(wjM1);
                        double d1=((double)wjP1+(double)wjM1);
                        //System.out.println("----- wjP "+wjP1+" : "+wjM1+" : "+d1);
                        TempArray.add(d1);
                        
                    } // k!=j
                
                    double max=Double.parseDouble(TempArray.get(0).toString());
                    for(int a1=1;a1<TempArray.size();a1++)
                    {
                        double e1=Double.parseDouble(TempArray.get(a1).toString());
                        max=Math.max(e1, max);
                    }   
                    arrList.add(max);
                }
            } // i
        
            double max=Double.parseDouble(arrList.get(0).toString());
            for(int a1=1;a1<arrList.size();a1++)
            {
                double e1=Double.parseDouble(arrList.get(a1).toString());
                max=Math.max(e1, max);
            }
            score1=max;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return score1;
    }
}
