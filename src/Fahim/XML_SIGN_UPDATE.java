package Fahim;

import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import java.util.*;
import javax.xml.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

import org.xml.sax.*;

import Fahim.XMLWork.Update.sign1;

import java.io.*;
import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.util.*;

class XMLWork extends JFrame {

	private JPanel buttonPanel;

	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	private JLabel l5;
	private JLabel l6;

	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTextField t5;
	private JTextField t6;

	private JButton StoreButt;
	private JButton ReadButt;
	private JButton SearchButt;
	private JButton UpdateButt;
	private JButton GenSigButt;
	private JButton VerSigButt;
	private JButton ResetButt;
	private JButton ExitButt;

	public XMLWork() {

		l1 = new JLabel("Subject Name: ", SwingConstants.LEFT);
		l2 = new JLabel("Year: ", SwingConstants.LEFT);
		l3 = new JLabel("Semester: ", SwingConstants.LEFT);
		l4 = new JLabel("Course No: ", SwingConstants.LEFT);
		l5 = new JLabel("Credit: ", SwingConstants.LEFT);
		l6 = new JLabel("Syllabus: ", SwingConstants.LEFT);

		t1 = new JTextField(5);
		t2 = new JTextField(5);
		t3 = new JTextField(5);
		t4 = new JTextField(5);
		t5 = new JTextField(5);
		t6 = new JTextField(5);

		StoreButt = new JButton("Store");
		StoreButtonHandler StoreB = new StoreButtonHandler();
		StoreButt.addActionListener(StoreB);

		ReadButt = new JButton("Read File");
		ReadButtonHandler ReadB = new ReadButtonHandler();
		ReadButt.addActionListener(ReadB);

		SearchButt = new JButton("Search");
		SearchButtonHandler SearchB = new SearchButtonHandler();
		SearchButt.addActionListener(SearchB);

		UpdateButt = new JButton("Update");
		UpdateButtonHandler UpdateB = new UpdateButtonHandler();
		UpdateButt.addActionListener(UpdateB);

		GenSigButt = new JButton("Generate signature");
		GenSigButtonHandler GenSigB = new GenSigButtonHandler();
		GenSigButt.addActionListener(GenSigB);

		VerSigButt = new JButton("Verify Signature");
		VerSigButtonHandler VerSigB = new VerSigButtonHandler();
		VerSigButt.addActionListener(VerSigB);

		ResetButt = new JButton("Reset");
		ResetButtonHandler Refresh = new ResetButtonHandler();
		ResetButt.addActionListener(Refresh);

		ExitButt = new JButton("Exit");
		ExitButtonHandler CloseOut = new ExitButtonHandler();
		ExitButt.addActionListener(CloseOut);

		setTitle("XML Document Handler");

		Container pane = getContentPane();

		pane.setLayout(new GridLayout(10, 2));

		pane.add(l1);
		pane.add(t1);
		pane.add(l2);
		pane.add(t2);
		pane.add(l3);
		pane.add(t3);
		pane.add(l4);
		pane.add(t4);
		pane.add(l5);
		pane.add(t5);
		pane.add(l6);
		pane.add(t6);

		pane.add(StoreButt);
		pane.add(UpdateButt);

		pane.add(ReadButt);
		pane.add(SearchButt);

		pane.add(GenSigButt);
		pane.add(VerSigButt);

		pane.add(ResetButt);
		pane.add(ExitButt);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private class StoreButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			String i1, i2, i3, i4, i5, i6;

			i1 = (t1.getText());
			i2 = (t2.getText());
			i3 = (t3.getText());
			i4 = (t4.getText());
			i5 = (t5.getText());
			i6 = (t6.getText());

			if ((i1.length() != 0) && (i2.length() != 0) && (i3.length() != 0)
					&& (i4.length() != 0) && (i5.length() != 0)
					&& (i6.length() != 0)) {

				Element rootElement;
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = null;
				try {
					docBuilder = builderFactory.newDocumentBuilder();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Document document = docBuilder.newDocument();
				File file1 = new File("file.xml");
				if (file1.exists()) {
					DocumentBuilderFactory fact = DocumentBuilderFactory
							.newInstance();
					try {
						DocumentBuilder builder = fact.newDocumentBuilder();
					} catch (ParserConfigurationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						document = docBuilder.parse(file1);
					} catch (SAXException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					rootElement = document.getDocumentElement();

					// root = node.getNodeName();
				} else {

					rootElement = document.createElement("Syllabus");
					document.appendChild(rootElement);
				}
				Element root = document.getDocumentElement();
				// Element rootElement = document.getDocumentElement();
				Element subject = document.createElement("subject");
				rootElement.appendChild(subject);

				Element name = document.createElement("name");

				name.appendChild(document.createTextNode(i1));
				subject.appendChild(name);

				Element year = document.createElement("year");
				year.appendChild(document.createTextNode(i2));
				subject.appendChild(year);

				Element sem = document.createElement("semester");
				sem.appendChild(document.createTextNode(i3));
				subject.appendChild(sem);

				Element courseno = document.createElement("courseno");
				courseno.appendChild(document.createTextNode(i4));
				subject.appendChild(courseno);

				Element credit = document.createElement("credit");
				credit.appendChild(document.createTextNode(i5));
				subject.appendChild(credit);

				Element syllabus = document.createElement("syllabus");
				syllabus.appendChild(document.createTextNode(i6));
				subject.appendChild(syllabus);

				root.appendChild(subject);

				try {

					DOMSource source = new DOMSource(document);
					TransformerFactory transformerFactory = TransformerFactory
							.newInstance();
					Transformer transformer = transformerFactory
							.newTransformer();
					StreamResult result = new StreamResult("file.xml");
					transformer.transform(source, result);
					JOptionPane.showMessageDialog(null, "Successfully stored");
				}

				catch (Exception ex) {
				}
				sign1 ac = new sign1();

				ac.getContentPane().setBackground(Color.LIGHT_GRAY);

				ac.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				ac.setBounds(400, 200, 300, 200);
				ac.setVisible(true);

				
			} else {
				JOptionPane.showMessageDialog(null,
						"Sorry!! please fill up all the fields", "XML Error",
						JOptionPane.WARNING_MESSAGE);
			}

		}

		class sign1 extends JFrame {
			private JPanel buttonPanel;

			private JLabel l2;
			private JLabel l3;

			JPasswordField t2;
			JPasswordField t3;

			private JButton OkButt;

			public sign1() {

				l2 = new JLabel("Public Key: ", SwingConstants.LEFT);
				l3 = new JLabel("Private Key: ", SwingConstants.LEFT);

				t2 = new JPasswordField(10);
				t3 = new JPasswordField(10);

				OkButt = new JButton("Ok");
				OkButtonHandler OkB = new OkButtonHandler();
				OkButt.addActionListener(OkB);

				setTitle("XML Document Handler");

				Container pane = getContentPane();

				pane.setLayout(new GridLayout(4, 2));

				pane.add(l2);
				pane.add(t2);
				pane.add(l3);
				pane.add(t3);
				pane.add(OkButt);


			}

			private class OkButtonHandler implements ActionListener {
				public void actionPerformed(ActionEvent e) {

					String i1, i2, i3, i4, i5, i6;

					i1 = (t1.getText());
					i2 = new String(t2.getPassword());
					i3 = new String(t3.getPassword());

					String xmlFilePath1 = "file.xml";
					String signedXmlFilePath1 = "digitallysignedfile.xml";

					String privateKeyFilePath1 = i3 + ".key";
					String publicKeyFilePath1 = i2 + ".key";
					XmlDigitalSignatureGenerator xmlSig1 = new XmlDigitalSignatureGenerator();
					xmlSig1.generateXMLDigitalSignature(xmlFilePath1,
							signedXmlFilePath1, privateKeyFilePath1,
							publicKeyFilePath1);
					JOptionPane.showMessageDialog(null, "Successfully signed");

				}
			}

		}

	}

	private class ReadButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String s = " ";
			try {

				File fXmlFile = new File("file.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);

				doc.getDocumentElement().normalize();

				NodeList nList = doc.getElementsByTagName("subject");

				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;

						s = s
								+ "\nSubject Name : "
								+ eElement.getElementsByTagName("name").item(0)
										.getTextContent()
								+ "\nYear : "
								+ eElement.getElementsByTagName("year").item(0)
										.getTextContent()
								+ "\nSemester : "
								+ eElement.getElementsByTagName("semester")
										.item(0).getTextContent()
								+ "\nCourse No : "
								+ eElement.getElementsByTagName("courseno")
										.item(0).getTextContent()
								+ "\nCredit : "
								+ eElement.getElementsByTagName("credit")
										.item(0).getTextContent()
								+ "\nSyllabus : "
								+ eElement.getElementsByTagName("syllabus")
										.item(0).getTextContent();

					}
					s = s + "\n";
				}

				JOptionPane.showMessageDialog(null, s);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}

	private class SearchButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			String s = " ";
			try {

				String i1 = (t1.getText());

				File fXmlFile = new File("file.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);

				doc.getDocumentElement().normalize();

				NodeList nList = doc.getElementsByTagName("subject");

				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;

						if (i1.equals(eElement.getElementsByTagName("name")
								.item(0).getTextContent())) {

							s = s
									+ "\nSubject Name : "
									+ eElement.getElementsByTagName("name")
											.item(0).getTextContent()
									+ "\nYear : "
									+ eElement.getElementsByTagName("year")
											.item(0).getTextContent()
									+ "\nSemester : "
									+ eElement.getElementsByTagName("semester")
											.item(0).getTextContent()
									+ "\nCourse No : "
									+ eElement.getElementsByTagName("courseno")
											.item(0).getTextContent()
									+ "\nCredit : "
									+ eElement.getElementsByTagName("credit")
											.item(0).getTextContent()
									+ "\nSyllabus : "
									+ eElement.getElementsByTagName("syllabus")
											.item(0).getTextContent();

						}
						s = s + "\n";
					}

				}

				JOptionPane.showMessageDialog(null, s);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}

	private class UpdateButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Update ac1 = new Update();

			ac1.getContentPane().setBackground(Color.LIGHT_GRAY);

			ac1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			ac1.setBounds(400, 200, 400, 400);
			ac1.setVisible(true);

		}
	}

	private class GenSigButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			try_pass ac = new try_pass();
			ac.getContentPane().setBackground(Color.LIGHT_GRAY);

			ac.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			ac.setBounds(400, 200, 300, 300);
			ac.setVisible(true);

		}
	}

	private class VerSigButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Verify ac = new Verify();
			ac.getContentPane().setBackground(Color.LIGHT_GRAY);

			ac.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			ac.setBounds(400, 200, 300, 300);
			ac.setVisible(true);
			
		}
	}

	private class ResetButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			t5.setText("");
			t6.setText("");

		}
	}

	private class ExitButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	public class Update extends JFrame {

		private JPanel buttonPanel;

		private JLabel l1;
		private JLabel l2;
		private JLabel l3;
		private JLabel l4;
		private JLabel l5;
		private JLabel l6;
		private JLabel l7;

		private JTextField t1;
		private JTextField t2;
		private JTextField t3;
		private JTextField t4;
		private JTextField t5;
		private JTextField t6;
		private JTextField t7;

		private JButton ConfirmButt;
		private JButton SignButt;
		private JButton Reset1Butt;
		private JButton Exit1Butt;

		public Update() {

			l1 = new JLabel("Subject Name: ", SwingConstants.LEFT);
			l2 = new JLabel("Year: ", SwingConstants.LEFT);
			l3 = new JLabel("Semester: ", SwingConstants.LEFT);
			l4 = new JLabel("Course No: ", SwingConstants.LEFT);
			l5 = new JLabel("Credit: ", SwingConstants.LEFT);
			l6 = new JLabel("Syllabus: ", SwingConstants.LEFT);
			l7 = new JLabel(
					"Enter the name of the Subject you want to update : ",
					SwingConstants.LEFT);

			t1 = new JTextField(5);
			t2 = new JTextField(5);
			t3 = new JTextField(5);
			t4 = new JTextField(5);
			t5 = new JTextField(5);
			t6 = new JTextField(5);
			t7 = new JTextField(5);

			// Icon point=new ImageIcon(getClass().getResource("point.JPG"));

			ConfirmButt = new JButton("Confirm");
			ConfirmButtonHandler ConfirmB = new ConfirmButtonHandler();
			ConfirmButt.addActionListener(ConfirmB);

			SignButt = new JButton("Sign");
			SignButtonHandler SignB = new SignButtonHandler();
			SignButt.addActionListener(SignB);

			Reset1Butt = new JButton("Reset");
			Reset1ButtonHandler Refresh = new Reset1ButtonHandler();
			Reset1Butt.addActionListener(Refresh);

			Exit1Butt = new JButton("Exit");
			Exit1ButtonHandler CloseOut = new Exit1ButtonHandler();
			Exit1Butt.addActionListener(CloseOut);

			setTitle("Update Form");

			Container pane1 = getContentPane();

			pane1.setLayout(new GridLayout(9, 2));

			pane1.add(l7);
			pane1.add(t7);
			pane1.add(l1);
			pane1.add(t1);
			pane1.add(l2);
			pane1.add(t2);
			pane1.add(l3);
			pane1.add(t3);
			pane1.add(l4);
			pane1.add(t4);
			pane1.add(l5);
			pane1.add(t5);
			pane1.add(l6);
			pane1.add(t6);

			pane1.add(ConfirmButt);
			pane1.add(SignButt);
			pane1.add(Reset1Butt);
			pane1.add(Exit1Butt);

			

		}

		private class ConfirmButtonHandler implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				String i1, i2, i3, i4, i5, i6, i7;

				i7 = (t7.getText());
				i1 = (t1.getText());
				i2 = (t2.getText());
				i3 = (t3.getText());
				i4 = (t4.getText());
				i5 = (t5.getText());
				i6 = (t6.getText());

				System.out.println(i1);

				try {

					File fXmlFile = new File("file.xml");
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(fXmlFile);

					NodeList nList = doc.getElementsByTagName("subject");

					String s = " ";

					for (int temp = 0; temp < nList.getLength(); temp++) {

						Node nNode = nList.item(temp);

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {

							Element eElement = (Element) nNode;

							if (i7.equals(eElement.getElementsByTagName("name")
									.item(0).getTextContent())) {

								if (i1.length() > 0) {
									eElement.getElementsByTagName("name")
											.item(0).setTextContent(i1);
								}
								if (i2.length() > 0) {

									eElement.getElementsByTagName("year")
											.item(0).setTextContent(i2);
								}
								if (i3.length() > 0) {
									eElement.getElementsByTagName("semester")
											.item(0).setTextContent(i3);
								}
								if (i4.length() > 0) {
									eElement.getElementsByTagName("courseno")
											.item(0).setTextContent(i4);
								}
								if (i5.length() > 0) {

									eElement.getElementsByTagName("credit")
											.item(0).setTextContent(i5);
								}
								if (i6.length() > 0) {
									eElement.getElementsByTagName("syllabus")
											.item(0).setTextContent(i6);
								}
								JOptionPane
										.showMessageDialog(null, "Confirmed");

							}

						}

						TransformerFactory transformerFactory = TransformerFactory
								.newInstance();
						Transformer transformer = transformerFactory
								.newTransformer();
						DOMSource source = new DOMSource(doc);
						StreamResult result = new StreamResult(new File(
								"file.xml"));
						transformer.transform(source, result);

						try {
							KeyPairGenerator keyGen = KeyPairGenerator
									.getInstance("DSA", "SUN");
							SecureRandom random = SecureRandom.getInstance(
									"SHA1PRNG", "SUN");

							keyGen.initialize(1024, random);

							KeyPair pair = keyGen.generateKeyPair();
							PrivateKey priv = pair.getPrivate();
							PublicKey pub = pair.getPublic();

							/*
							 * Create a Signature object and initialize it with
							 * the private key
							 */

							Signature dsa = Signature.getInstance(
									"SHA1withDSA", "SUN");

							dsa.initSign(priv);

							/* Update and sign the data */

							FileInputStream fis = new FileInputStream(
									"file.xml");
							BufferedInputStream bufin = new BufferedInputStream(
									fis);
							byte[] buffer = new byte[2048];
							int len;
							while (bufin.available() != 0) {
								len = bufin.read(buffer);
								dsa.update(buffer, 0, len);
							}
							;

							bufin.close();

							/*
							 * Now that all the data to be signed has been read
							 * in, generate a signature for it
							 */

							byte[] realSig = dsa.sign();

							/* Save the signature in a file */
							FileOutputStream sigfos = new FileOutputStream(
									"rafi");
							sigfos.write(realSig);

							sigfos.close();

							/* Save the public key in a file */
							byte[] key = pub.getEncoded();
							FileOutputStream keyfos = new FileOutputStream(
									"fahim");
							keyfos.write(key);

							keyfos.close();
						} catch (Exception e1) {
							System.err.println("Caught exception "
									+ e1.toString());
						}

					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		}

		private class SignButtonHandler implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				sign1 ac = new sign1();

				ac.getContentPane().setBackground(Color.LIGHT_GRAY);

				ac.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				ac.setBounds(400, 200, 200, 200);
				ac.setVisible(true);

			}
		}

		class sign1 extends JFrame {
			private JPanel buttonPanel;

			private JLabel l2;
			private JLabel l3;

			JPasswordField t2;
			JPasswordField t3;

			private JButton OkButt;

			public sign1() {

				l2 = new JLabel("Public Key: ", SwingConstants.LEFT);
				l3 = new JLabel("Private Key: ", SwingConstants.LEFT);

				t2 = new JPasswordField(10);
				t3 = new JPasswordField(10);

				OkButt = new JButton("Ok");
				OkButtonHandler OkB = new OkButtonHandler();
				OkButt.addActionListener(OkB);

				setTitle("XML Document Handler");

				Container pane = getContentPane();

				pane.setLayout(new GridLayout(4, 2));

				pane.add(l2);
				pane.add(t2);
				pane.add(l3);
				pane.add(t3);
				pane.add(OkButt);

				

			}

			private class OkButtonHandler implements ActionListener {
				public void actionPerformed(ActionEvent e) {

					String i1, i2, i3, i4, i5, i6;

					i1 = (t1.getText());
					i2 = new String(t2.getPassword());
					i3 = new String(t3.getPassword());

					String xmlFilePath1 = "file.xml";
					String signedXmlFilePath1 = "digitallysignedfile.xml";

					String privateKeyFilePath1 = i3 + ".key";
					String publicKeyFilePath1 = i2 + ".key";
					XmlDigitalSignatureGenerator xmlSig1 = new XmlDigitalSignatureGenerator();
					xmlSig1.generateXMLDigitalSignature(xmlFilePath1,
							signedXmlFilePath1, privateKeyFilePath1,
							publicKeyFilePath1);
					JOptionPane.showMessageDialog(null, "Successfully signed");

				}
			}

		}

		private class Reset1ButtonHandler implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
				t6.setText("");

			}
		}

		private class Exit1ButtonHandler implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		}

	}

	class try_pass extends JFrame {
		private JPanel buttonPanel;

		private JLabel l1;
		private JLabel l2;

		JTextField p1;
		JTextField p2;

		private JButton OkButt;

		public try_pass() {
			l1 = new JLabel("Public Key: ", SwingConstants.LEFT);
			l2 = new JLabel("Private Key: ", SwingConstants.LEFT);

			p1 = new JTextField(10);
			p2 = new JTextField(10);

			OkButt = new JButton("Ok");
			OkButtonHandler OkB = new OkButtonHandler();
			OkButt.addActionListener(OkB);

			setTitle("XML Document Handler");

			Container pane = getContentPane();

			pane.setLayout(new GridLayout(3, 2));

			pane.add(l1);
			pane.add(p1);
			pane.add(l2);
			pane.add(p2);
			pane.add(OkButt);

			

		}

		private class OkButtonHandler implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String PrivateKey = p2.getText();
				String PublicKey = p1.getText();
				String keysDirPath = ".";
				KryptoUtil util = new KryptoUtil();
				String result = util.storeKeyPairs(keysDirPath, PrivateKey,
						PublicKey);
				if (result.equals("success")) {
					JOptionPane.showMessageDialog(null,
							"Successful Digital Signature Creation",
							"Signature Created", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	class Verify extends JFrame {
		private JPanel buttonPanel;

		private JLabel l1;
		

		JTextField p1;
		

		private JButton OkButt;

		public Verify() {
			l1 = new JLabel("Public Key: ", SwingConstants.LEFT);
			

			p1 = new JTextField(10);
			

			OkButt = new JButton("Ok");
			OkButtonHandler OkB = new OkButtonHandler();
			OkButt.addActionListener(OkB);

			setTitle("XML Document Handler");

			Container pane = getContentPane();

			pane.setLayout(new GridLayout(3, 2));

			pane.add(l1);
			pane.add(p1);
			
			pane.add(OkButt);

			

		}

		private class OkButtonHandler implements ActionListener {
			public void actionPerformed(ActionEvent e) {
			
				String PublicKey = p1.getText();
				String keysDirPath = ".";
				String signedXmlFilePath = "digitallysignedfile.xml";
		        String publicKeyFilePath = PublicKey+".key";
		        try {
		            boolean validFlag = XmlDigitalSignatureVerifier.
		                    isXmlDigitalSignatureValid(signedXmlFilePath, publicKeyFilePath);
		            System.out.println("Validity of XML Digital Signature : " + validFlag);
		            if(validFlag==true)
		            {
		            JOptionPane.showMessageDialog(null,
							"File Validation Successful", "XML Error",
							JOptionPane.WARNING_MESSAGE);
		            }
		            else
		            {
		            	JOptionPane.showMessageDialog(null,
								"Sorry!! Validation failed", "XML Error",
								JOptionPane.WARNING_MESSAGE);
		            }
		        } catch (Exception ex) {
		        	
		            ex.printStackTrace();
		        }
				
		}
	}
	}

	static class XML_SIGN_UPDATE {
		public static void main(String[] args) throws Exception {
			XMLWork ac = new XMLWork();

			ac.getContentPane().setBackground(Color.LIGHT_GRAY);

			ac.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			ac.setBounds(400, 200, 500, 400);
			ac.setVisible(true);

		}
	}
}
