package com.vocaber;

//import the required packages
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TransformerDesign {

	double inputVoltage, outputVoltage;
	double rating;

	Scanner scn;

	TransformerDesign(double v1, double v2, double rt) {

		inputVoltage = v1;
		outputVoltage = v2;
		rating = rt;
		scn = null;
	}

	public void calculations() {


		scn = new Scanner(System.in);

		 double Kt = 0.0;
		 System.out.println("Enter the value of Kt: between 0.6 and 0.7 [0.7]");
		 Kt = scn.nextDouble();
		 
		System.out.println("Enter the Value of Bm for Core Material [1.7]");
		double Bm = scn.nextDouble();

		System.out
				.println("Enter the value of proportionality constant(between D and d) [1.9] ");
		double rel = scn.nextDouble();

		System.out.println("Enter the value of standard ratio of L and Ww [3]");
		double ratio = scn.nextDouble();

		System.out.println("Enter the relation between Ay and Ai [1.12]");
		double con = scn.nextDouble();

		System.out.println("Enter value of current density in A/mm2 [3]");
		double f = scn.nextDouble();

		System.out.println("Enter the value of pi for core material used [1]");
		double pi = scn.nextDouble();
		System.out.println("Enter the value of density [7600]");
		double rho = scn.nextDouble();


			// Emf per turn of transformer

			double Et = 0.0;
			Et = getEmfPerTurn(Kt);

			// Area of Core

			double Ai = 0.0;
			Ai = getCoreArea(Et, Bm);

			// width of a leg
			double d = 0.0;
			d = get_d(Ai);

			// Distance between legs

			double D = 0.0;
			D = get_D(d, rel);

			// Width of Window
			double Ww = 0.0;
			Ww = getWidthOfWindow(D, d);

			// height of window

			double L = getWindowHeight(Ww, ratio);

			// area of yoke

			double Ay = getYokeArea(Ai, con);

			// depth of yoke
			double dy = getDepthOfYoke(d);

			// height of yoke
			double hy = getHeightOfYoke(Ay, dy);

			// width of core
			double W = getWidthOfCore(D, d);

			// height of core
			double H = getHeightOfCore(L, hy);

			// flux density in yoke
			double By = getFluxDensity(Ai, Ay, Bm);

			// primary current
			double I1 = getWindingCurrent(rating, inputVoltage);

			// secondary current
			double I2 = getWindingCurrent(rating, outputVoltage);

			// sectional area of primary winding

			double a1 = getSectionalArea(I1, f);

			// sectional area of secondary winding

			double a2 = getSectionalArea(I2, f);

			// number of primary turns

			double N1 = getNumberOfTurns(inputVoltage, Et);

			// number of secondary turns

			double N2 = getNumberOfTurns(outputVoltage, Et);

			// width of transformer

			double Wt = getWidthOfTransformer(D, d);

			// depth of transformer

			double Lt = getDepthOfTransformer(d);

			// height of transformer

			double Ht = getHeightOfTransformer(H);

			// Iron Losses

			// Core Iron Loss

			double Pi = pi * rho * 3 * L * Ai;

			// Yoke Iron Loss

			double Py = pi * rho * 2 * W * Ay;

			// Total Iron Loss

			double IronLoss = Pi + Py;

			// Core component of no load current

			double Ic = IronLoss / (3 * inputVoltage);

			// mmf core

			double mmfi = 3 * L * N1 * I1;

			// mmf yoke

			double mmfy = 2 * W * N2 * I2;

			// total mmf

			double mmfTotal = mmfi + mmfy;

			// magnetising Imax

			double Im_max = mmfTotal / (3 * N1 * Math.sqrt(2.0));

			// length of mean turn

			double Lmt = 3.142 * d;

			// R1dc

			double R1dc = (0.021 * Math.pow(10, -6) * Lmt * N1) / a1;

			// R1ac

			double R1ac = 1.2 * R1dc;

			// R2dc
			double R2dc = (0.021 * Math.pow(10, -6) * Lmt * N2) / a2;

			// R2ac

			double R2ac = 1.2 * R2dc;

			// Copper Losses

			// Primary Copper Loss

			double Cu1 = 3 * Math.pow(I1, 2) * R1ac;

			// Secondary Copper Loss

			double Cu2 = 3 * Math.pow(I2, 2) * R2ac;

			// Total Copper Loss

			double CopperLoss = Cu1 + Cu2;

			// Efficiency

			double eff = (rating * 1000)
					/ ((rating * 1000) + IronLoss + CopperLoss);

			// Surface area of tank

			double St = 2 * (Wt + Lt) * Ht;

			// rise in temp

			double temp = (IronLoss + CopperLoss) / (12.5 * St);

			System.out
					.println("\nTRANSFORMER DIMENSIONS\n--------------------------");
			System.out.println();

			System.out.println("Emf per turn= " + Et);
			System.out.println("Area of Core= " + Ai + " m2");
			System.out.println("width of leg= " + d + " m");
			System.out.println("Distance between legs= " + D + " m");
			System.out.println("Window width= " + Ww + " m");
			System.out.println("Window height= " + L + " m");
			System.out.println("Yoke Area= " + Ay + " m2");
			System.out.println("Depth of yoke= " + dy + " m");
			System.out.println("Height of yoke= " + hy + " m");
			System.out.println("Width of core= " + W + " m");
			System.out.println("Height of core= " + H + " m");
			System.out.println("Flux density= " + By);
			System.out.println("Primary Winding Current= " + I1 + " Amp");
			System.out.println("Secondary Winding Current= " + I2 + " Amp");
			System.out.println("Primary Winding Sectional Area= " + a1 + " m2");
			System.out.println("Secondary Winding Sectional Area= " + a2
					+ " m2");
			System.out.println("Primary Number of turns= " + N1);
			System.out.println("Secondary Number of turns= " + N2);
			System.out.println("Width of Transformer= " + Wt + " m");
			System.out.println("Depth of Transformer= " + Lt + " m");
			System.out.println("Height of Transformer= " + Ht + " m");

			System.out
					.println("\nTRANSFORMER PERFORMANCE\n------------------------");
			System.out.println();

			System.out.println("Iron losses= " + IronLoss + " W");
			System.out.println("Core component of no load current= " + Ic
					+ " Amp");
			System.out.println("magnetising Imax= " + Im_max + " Amp");
			System.out.println("Primary Winding resistance= " + R1ac + " ohms");
			System.out.println("Secondary Winding resistance= " + R2ac
					+ " ohms");
			System.out.println("Copper losses= " + CopperLoss + " W");
			System.out.println("Efficiency= " + eff);
			System.out.println("Surface Area of tank= " + St + " m2");
			System.out.println("Temperature rise= " + temp + " degrees");
			System.out.println();


	}

	public double getEmfPerTurn(double Kt) {

		double Et = Kt * Math.sqrt(rating);
		return Et;

	}

	public double getCoreArea(double Et, double Bm) {

		double Ai = Et / (4.44 * 50 * Bm);
		return Ai;
	}

	public double get_d(double Ai) {

		double d = Math.sqrt(Ai / 0.45);
		return d;
	}

	public double get_D(double d, double K) {

		double D = K * d;
		return D;
	}

	public double getWidthOfWindow(double D, double d) {

		double width = D - d;
		return width;
	}

	public double getWindowHeight(double width, double ratio) {

		double height = ratio * width;
		return height;
	}

	public double getYokeArea(double Ai, double con) {

		double Ay = con * Ai;
		return Ay;
	}

	public double getDepthOfYoke(double d) {

		double dy = d / Math.sqrt(2);
		return dy;
	}

	public double getHeightOfYoke(double Ay, double dy) {

		double hy = Ay / dy;
		return hy;
	}

	public double getWidthOfCore(double D, double d) {

		double W = 2 * D + d;
		return W;
	}

	public double getHeightOfCore(double L, double hy) {

		double H = L + 2 * hy;
		return H;
	}

	public double getFluxDensity(double Ai, double Ay, double Bm) {

		double By = (Ai * Bm) / Ay;
		return By;
	}

	public double getWindingCurrent(double rat, double Voltage) {

		double I = (rat * 1000) / (3 * Voltage);
		return I;
	}

	public double getSectionalArea(double I, double f) {

		double a = I / (f*1000000);
		return a;
	}

	public int getNumberOfTurns(double voltage, double Et) {

		int N = (int) (voltage / Et);
		N = N + 1;
		return N;

	}

	public double getWidthOfTransformer(double D, double d) {

		double Wt = 2 * D + d;
		Wt = Wt + (2 * 0.15 * Wt);
		return Wt;

	}

	public double getDepthOfTransformer(double d) {

		double Lt = d + (2 * 0.15 * d);
		return Lt;
	}

	public double getHeightOfTransformer(double H) {

		double Ht = H + (0.15 * H);
		return Ht;
	}
}
