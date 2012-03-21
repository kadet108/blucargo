package com.blucargo;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jivesoftware.openfire.SessionManager;
import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.container.Plugin;
import org.jivesoftware.openfire.container.PluginManager;
import org.jivesoftware.openfire.event.SessionEventDispatcher;
import org.jivesoftware.openfire.group.GroupManager;
import org.jivesoftware.openfire.user.User;
import org.jivesoftware.openfire.user.UserAlreadyExistsException;
import org.jivesoftware.openfire.user.UserManager;
import org.jivesoftware.openfire.user.UserNotFoundException;
import org.jivesoftware.openfire.vcard.VCardManager;
import org.jivesoftware.smackx.packet.VCard;
import org.jivesoftware.util.AlreadyExistsException;
import org.xmpp.component.ComponentManager;
import org.xmpp.packet.JID;

import com.blucargo.model.AcceptedOffer;
import com.blucargo.model.CargoOffer;
import com.blucargo.model.Comment;
import com.blucargo.model.EndedOffer;
import com.blucargo.model.FavouriteOffer;
import com.blucargo.model.OfferAcceptance;
import com.blucargo.model.SearchCriteria;
import com.blucargo.persistence.MainPersistenceModule;
import com.blucargo.services.AcceptedOfferService;
import com.blucargo.services.CarBodyService;
import com.blucargo.services.CargoOfferService;
import com.blucargo.services.CityService;
import com.blucargo.services.CommentService;
import com.blucargo.services.CountryService;
import com.blucargo.services.EndedOfferService;
import com.blucargo.services.FavouriteOfferService;
import com.blucargo.services.OfferAcceptanceService;
import com.blucargo.services.RegistrationDataService;
import com.blucargo.services.SearchCriteriaService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class BlucargoPlugin implements Plugin {

	@SuppressWarnings("unused")
	private String serviceName;
	@SuppressWarnings("unused")
	private SessionManager sessionManager;
	@SuppressWarnings("unused")
	private GroupManager groupManager;
	private List<JID> allowedUsers;
	@SuppressWarnings("unused")
	private boolean groupMembersAllowed;
	@SuppressWarnings("unused")
	private boolean disableGroupPermissions;
	@SuppressWarnings("unused")
	private ComponentManager componentManager;
	@SuppressWarnings("unused")
	private PluginManager pluginManager;

	private RegistrationDataService registrationDataService;
	private AcceptedOfferService acceptedOfferService;
	private CargoOfferService cargoOfferService;
	private CarBodyService carBodyService;
	private CityService cityService;
	private CountryService countryService;
	private CommentService commentService;
	private EndedOfferService endedOfferService;
	private FavouriteOfferService favouriteOfferService;
	private OfferAcceptanceService offerAcceptanceService;
	private SearchCriteriaService searchCriteriaService;
	

	public BlucargoPlugin() {
		System.out.println("Cargo plugin constructor");
		serviceName = "CargoPlugin";
	}

	private void createHandlers() {

		// //// ACCEPTED OFFERS ///////
		BlucargoRequestHandler addAcceptedOffersHandler = new BlucargoRequestHandler(
				"http://myCargo.com/addAcceptedOffers") {

			@Override
			public Object process(Object object) {
				try {
					acceptedOfferService
							.saveAcceptedOffers((ArrayList<AcceptedOffer>) object);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}

		};

		BlucargoRequestHandler getAcceptedOffersHandler = new BlucargoRequestHandler(
				"http://myCargo.com/getAcceptedOffers") {

			@Override
			public Object process(Object object) {
				try {
					return acceptedOfferService
							.getAcceptedCargoOffersByOwner(this.from);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}

		};

		BlucargoRequestHandler removeAcceptedOffersByCargoOfferAndOwnerHandler = new BlucargoRequestHandler(
				"http://myCargo.com/removeAcceptedOffersByCargoOfferAndOwner") {

			@Override
			public Object process(Object object) {
				try {
					acceptedOfferService
							.removeAcceptedOffersByCargoOfferAndOwner(
									(List<CargoOffer>) object, this.from);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;

			}

		};

		BlucargoRequestHandler removeAcceptedOffersHandler = new BlucargoRequestHandler(
				"http://myCargo.com/removeAcceptedOffers") {

			@Override
			public Object process(Object object) {
				try {
					acceptedOfferService
							.removeAcceptedOffers((List<AcceptedOffer>) object);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;

			}

		};

		BlucargoRequestHandler getAcceptedOfferByCargoOfferIdAndUserNameHandler = new BlucargoRequestHandler(
				"http://myCargo.com/getAcceptedOfferByCargoOfferAndUserName") {

			@Override
			public Object process(Object object) {
				try {
					return acceptedOfferService
							.getAcceptedOfferByCargoOfferIdAndUserName(
									(Long) object, this.from);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}

		};
		// //// ACCEPTED OFFERS ///////

		// //// CAR BODY ///////
		BlucargoRequestHandler getCarBodiesHandler = new BlucargoRequestHandler(
				"http://myCargo.com/getCarBodies") {

			@Override
			public Object process(Object object) {
				Object bodies = carBodyService.findAll();
				return bodies;
			}

		};
		// //// BODY ///////

		// //// COUNTRY///////
		BlucargoRequestHandler getCountriesHandler = new BlucargoRequestHandler(
				"http://myCargo.com/getCountries") {

			@Override
			public Object process(Object object) {
				return countryService.findAll();
			}

		};

		BlucargoRequestHandler findAllCountryHandler = new BlucargoRequestHandler(
				"http://myCargo.com/findAllCountry") {

			@Override
			public Object process(Object object) {
				return countryService.findAllCountry();
			}

		};
		// //// COUNTRY ///////

		// //// CITIES ///////
		BlucargoRequestHandler getCitiesHandler = new BlucargoRequestHandler(
				"http://myCargo.com/getCities") {

			@Override
			public Object process(Object object) {
				return cityService.findAll();
			}

		};

		BlucargoRequestHandler getCitiesByCountryHandler = new BlucargoRequestHandler(
				"http://myCargo.com/getCitiesByCountry") {

			@Override
			public Object process(Object object) {
				return cityService.findCitiesByCountry((String) object);
			}

		};
		// //// CITIES ///////

		// //// CARGO OFFERS ///////
		BlucargoRequestHandler addCargoOffersHandler = new BlucargoRequestHandler(
				"http://myCargo.com/addCargoOffers") {

			@Override
			public Object process(Object object) {
				try {
					cargoOfferService.save((ArrayList<CargoOffer>) object);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;

			}

		};

		BlucargoRequestHandler getCargoOffersHandler = new BlucargoRequestHandler(
				"http://myCargo.com/getCargoOffers") {

			@Override
			public Object process(Object object) {
				try {
					return cargoOfferService
							.findAllThatAreNotAcceptedNorDeleted();
				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}

		};

		BlucargoRequestHandler findOfferByIdHandler = new BlucargoRequestHandler(
				"http://myCargo.com/findOfferById") {

			@Override
			public Object process(Object object) {
				try {
					return cargoOfferService.findOfferById((Long) object);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}

		};

		BlucargoRequestHandler removeCargoOffersHandler = new BlucargoRequestHandler(
				"http://myCargo.com/removeCargoOffers") {

			@Override
			public Object process(Object object) {
				try {
					cargoOfferService
							.removeCargoOffers((List<CargoOffer>) object);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}

		};
		// //// CARGO OFFERS ///////

		// //// COMMENT //////
		BlucargoRequestHandler addCommentHandler = new BlucargoRequestHandler(
				"http://myCargo.com/addComment") {

			@Override
			public Object process(Object object) {
				Comment comment = (Comment) object;
				comment.setAuthor(this.from);
				commentService.save(comment);
				return null;
			}

		};

		BlucargoRequestHandler removeCommentHandler = new BlucargoRequestHandler(
				"http://myCargo.com/removeComment") {

			@Override
			public Object process(Object object) {
				commentService.remove((Comment) object);
				return null;
			}


		};

		BlucargoRequestHandler findCommentsByOfferIdHandler = new BlucargoRequestHandler(
				"http://myCargo.com/findCommentsByOfferId") {

			@Override
			public Object process(Object object) {
				return commentService.findCommentsByOfferId((Long) object);
			}


		};

		BlucargoRequestHandler findCommentsByAuthorHandler = new BlucargoRequestHandler(
				"http://myCargo.com/findCommentsByAuthor") {

			@Override
			public Object process(Object object) {
				return commentService.findCommentsByAuthor((String) object);
			}

		};

		BlucargoRequestHandler findAllCommentsForUserHandler = new BlucargoRequestHandler(
				"http://myCargo.com/findAllCommentsForUser") {

			@Override
			public Object process(Object object) {
				return commentService.findAllCommentsForUser((String) object);
			}

		};

		BlucargoRequestHandler findAllCommentsForUserSimpleHandler = new BlucargoRequestHandler(
		"http://myCargo.com/findAllCommentsForUserSimple") {

			@Override
			public Object process(Object object) {
				return commentService.findAllCommentsForUserSimple((String) object);
			}
		
		};

		// //// COMMENT //////

		// //// FAVOURITE OFFERS ///////
		BlucargoRequestHandler addFavouriteOffersHandler = new BlucargoRequestHandler(
				"http://myCargo.com/addFavouriteOffers") {

			@Override
			public Object process(Object object) {
				List<FavouriteOffer>  favouriteOffers  = (List<FavouriteOffer>) object;
				favouriteOfferService.saveFavouriteOffers(favouriteOffers);
				return null;
			}

		};

		BlucargoRequestHandler getFavouriteOffersHandler = new BlucargoRequestHandler(
				"http://myCargo.com/getFavouriteOffers") {

			@Override
			public Object process(Object object) {
				try {
					return favouriteOfferService
							.getFavouriteCargoOffersByOwner(this.from);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

		};

		BlucargoRequestHandler removeFavouriteOffersHandler = new BlucargoRequestHandler(
				"http://myCargo.com/removeFavouriteOffers") {

			@Override
			public Object process(Object object) {
				try {
					favouriteOfferService
							.removeFavouriteOffersByCargoOfferAndOwner(
									(List<CargoOffer>) object, this.from);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}

		};
		// //// FAVOURITE OFFERS ///////

		// //// ENDED OFFERS ///////
		BlucargoRequestHandler addEndedOffersHandler = new BlucargoRequestHandler(
				"http://myCargo.com/addEndedOffers") {

			@Override
			public Object process(Object object) {
				try {
					endedOfferService
							.saveEndedOffers((ArrayList<EndedOffer>) object);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;

			}

		};

		BlucargoRequestHandler getEndedOffersHandler = new BlucargoRequestHandler(
				"http://myCargo.com/getEndedOffers") {

			@Override
			public Object process(Object object) {
				try {
					return endedOfferService
							.getEndedCargoOffersByOwner(this.from);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}

		};

		BlucargoRequestHandler removeEndedOffersHandler = new BlucargoRequestHandler(
				"http://myCargo.com/removeEndedOffers") {

			@Override
			public Object process(Object object) {
				try {
					endedOfferService.removeEndedOffersByCargoOfferAndOwner(
							(List<CargoOffer>) object, this.from);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;

			}

		};

		BlucargoRequestHandler findEndedOfferByOwnerHandler = new BlucargoRequestHandler(
				"http://myCargo.com/findEndedOfferByOwner") {

			@Override
			public Object process(Object object) {
				return endedOfferService.findEndedOfferByOwner((String) object);
			}

		};
		// //// ACCEPTED OFFERS ///////

		// //// OFFER ACCEPTANCE
		BlucargoRequestHandler initiateOfferAcceptance = new BlucargoRequestHandler(
				"http://myCargo.com/initiateOfferAcceptance") {

			@Override
			public Object process(Object object) {
				try {
					OfferAcceptance offerAcceptance = (OfferAcceptance) object;
					CargoOffer cargoOffer = cargoOfferService
							.findById(offerAcceptance.getOfferId());
					offerAcceptanceService.initiateOffer(
							offerAcceptance.getInitiator(),
							offerAcceptance.getInitiated(), cargoOffer);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;

			}

		};

		BlucargoRequestHandler initiatorLogsOutOfferAcceptance = new BlucargoRequestHandler(
				"http://myCargo.com/initiatorLogsOutOfferAcceptance") {

			@Override
			public Object process(Object object) {
				try {
					offerAcceptanceService.initiatorLogsOut((String) object);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}
		};

		// //// OFFER ACCEPTANCE

		// /// SEARCH CRITERIA
		BlucargoRequestHandler addSearchCriteriaHandler = new BlucargoRequestHandler(
				"http://myCargo.com/addSearchCritiera") {

			@Override
			public Object process(Object object) {
				SearchCriteria searchCriteria = (SearchCriteria) object;
				searchCriteria.setUserName(this.from);
				searchCriteriaService.save(searchCriteria);
				return null;
			}

		};

		BlucargoRequestHandler removeSearchCriteriaHandler = new BlucargoRequestHandler(
				"http://myCargo.com/removeSearchCritiera") {

			@Override
			public Object process(Object object) {
				Long id = (Long) object;
				searchCriteriaService.removeById(id);
				return null;
			}

		};

		BlucargoRequestHandler findSearchCriteriasByUserHandler = new BlucargoRequestHandler(
				"http://myCargo.com/findSearchCriteriasByUser") {

			@Override
			public Object process(Object object) {
				return searchCriteriaService.findByUserName(this.from);
			}

		};

		BlucargoRequestHandler deleteSearchCriteriaByUserNameAndNameHandler = new BlucargoRequestHandler(
				"http://myCargo.com/deleteSearchCriteriaByUserNameAndName") {

			@Override
			public Object process(Object object) {
				searchCriteriaService.deleteByUserNameAndCriteriaName(
						this.from, (String) object);
				return null;
			}

		};

		// /// SEARCH CRITERIA

		// /// REGISTER USER //////
		BlucargoRequestHandler getUserByRegistrationNumberHandler = new BlucargoRequestHandler(
				"http://myCargo.com/getUserByRegistrationNumber") {

			@Override
			public Object process(Object object) {
				return registrationDataService
						.getUserByRegistrationNumber((String) object);
			}

		};
		
		BlucargoRequestHandler deleteAllByRegIdHandler = new BlucargoRequestHandler(
		"http://myCargo.com/deleteAllByRegId") {

			@Override
		public Object process(Object object) {
				registrationDataService.deleteAllByRegId((String) object);
				return null;
		}

		};
		
		BlucargoRequestHandler checkLoginInTableHandler = new BlucargoRequestHandler(
		"http://myCargo.com/checkLoginInTable") {

			@Override
			public Object process(Object object) {
					return registrationDataService
					.checkLoginInTable((String) object);
			}

		};

		
		///// REGISTER USER
		
		
		BlucargoRequestHandler isUserHandler = new BlucargoRequestHandler(
		"http://myCargo.com/isUser") {

			@Override
			public Object process(Object object) {
				String user = (String) object;
				boolean bool = true;
					try {
						UserManager.getInstance().getUser(user);
					} catch (UserNotFoundException e) {
						bool = false;
					}
				return bool;
			}

			
			
//			@Override
//			public Object getObject(Object value) {
//		
//				String user = (String) value;
//				boolean bool = true;
//					try {
//						UserManager.getInstance().getUser(user);
//					} catch (UserNotFoundException e) {
//						bool = false;
//					}
//				return bool;
//			}
//	
//			@Override
//			public void setObject(Object object) {
//				return;
//			}
		};
		
		


		// /// REGISTER USER //////


		BlucargoRequestHandler getUserJidFromNickNameHandler = new BlucargoRequestHandler(
				"http://myCargo.com/getUserJIDFromNickname") {

			@Override
			public Object process(Object object) {
				return this.from
						+ "@"
						+ XMPPServer.getInstance().getServerInfo()
								.getHostname();
			}
		};

		BlucargoRequestHandler getEmployeesOfHandler = new BlucargoRequestHandler(
				"http://myCargo.com/getEmployeesOf") {

			@Override
			public Object process(Object object) {
				String employer = (String) object;

				BlucargoUserProvider cargoUserProvider = new BlucargoUserProvider();

				List<String> employeesOf = null;

				try {
					employeesOf = cargoUserProvider.getEmployeesOf(employer);
				} catch (UserNotFoundException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				}

				if (employeesOf != null && employeesOf.size() == 0) {
					return null;
				}

				List<String> verifiedEmployees = new ArrayList<String>();

				for (String employee : employeesOf) {
					try {
						UserManager.getInstance().getUser(employee);
					} catch (UserNotFoundException e) {
						continue;
					}
					verifiedEmployees.add(employee);

				}

				return verifiedEmployees;
			}

		};

		BlucargoRequestHandler getUserPropertyHandler = new BlucargoRequestHandler(
				"http://myCargo.com/getUserProperty") {

			@Override
			public Object process(Object object) {

				Object[] params = (Object[]) object;
				String userName = (String) params[0];
				String key = (String) params[1];

				return User.getPropertyValue(userName, key);
			}

		};

		BlucargoRequestHandler getUserVCardPropertyHandler = new BlucargoRequestHandler(
				"http://myCargo.com/getUserVCardProperty") {

			@Override
			public Object process(Object object) {

				Object[] params = (Object[]) object;
				String userName = (String) params[0];
				String key = (String) params[1];

				String returnValue = VCardManager.getInstance()
						.getVCardProperty(userName, key);

				return returnValue;

			}

		};

		BlucargoRequestHandler setUserPropertyHandler = new BlucargoRequestHandler(
				"http://myCargo.com/setUserProperty") {

			@Override
			public Object process(Object object) {
				Object[] params = (Object[]) object;
				String userName = (String) params[0];
				String key = (String) params[1];
				String value = (String) params[2];

				try {
					User user = UserManager.getInstance().getUser(userName);
					user.getProperties().put(key, value);
				} catch (UserNotFoundException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}

				return null;
			}

		};

		BlucargoRequestHandler saveOrUpdateUsersHandler = new BlucargoRequestHandler(
				"http://myCargo.com/saveOrUpdateUsers") {

			@Override
			public Object process(Object object) {

				List<Map<String, String>> employees = (List<Map<String, String>>) object;

				if (employees.size() == 0) {
					return null;
				}

				String employer = employees.get(0).get("employeeOf");

				// First we will perform a check if the users exist and if
				// they are employees of employer
				for (Map<String, String> employee : employees) {

					User user = null;
					try {
						String nickname = employee.get("nickName");
						user = UserManager.getInstance().getUser(nickname);
					} catch (UserNotFoundException e) {

					}

					if (user != null) {
						String employeeOf = user.getProperties().get(
								"employeeOf");
						if (employeeOf != null
								&& employeeOf
										.equals(employee.get("employeeOf"))) {
							// OK
						} else {
							return "U�ytkownik " + employee.get("nickName")
									+ "ju� istnieje.";
						}
					} else {
						String username = employee.get("nickName");
						String password = employee.get("password");

						// We check password only if that user does not exist,
						// otherwise we dont need to update password
						if (password == null || password.isEmpty()) {
							return String.format(
									"Nie podano hasla dla u�ytkownika %s.",
									username);
						}
					}

					String username = employee.get("nickName");
					// String password = employee.get("password");
					String name = employee.get("firstName");
					String email = employee.get("email");

					if (username == null || username.isEmpty()) {
						return "Nie podano nazwy u�ytkownika.";
					}

					if (name == null || name.isEmpty()) {
						return String.format(
								"Nie podano imienia dla u�ytkownika %s.",
								username);
					}

					if (email == null || email.isEmpty()) {
						return String.format(
								"Nie podano adresu email dla u�ytkownika %s.",
								username);
					}
				}

				// Create or update users
				for (Map<String, String> employee : employees) {
					try {
						User user = UserManager.getInstance().getUser(
								employee.get("nickName"));

					} catch (UserNotFoundException e) {
						String username = employee.get("nickName");
						String password = employee.get("password");
						String name = employee.get("firstName");
						String email = employee.get("email");
						try {
							User user = UserManager.getInstance().createUser(
									username, password, name, email);
							user.getProperties().put("employeeOf", employer);
							// UserManager.getInstance().get

						} catch (UserAlreadyExistsException e1) {
							// TODO Auto-generated catch block
							// e1.printStackTrace();
							// This shoud not happen
						}
					}

					SAXReader xmlReader = new SAXReader();

					String nickName = (employee.get("nickName") != null) ? employee
							.get("nickName") : "";
					String firstName = (employee.get("firstName") != null) ? employee
							.get("firstName") : "";
					String lastName = (employee.get("lastName") != null) ? employee
							.get("lastName") : "";
					String email = (employee.get("email") != null) ? employee
							.get("email") : "";
					String phone = (employee.get("phone") != null) ? employee
							.get("phone") : "";
					String fax = (employee.get("fax") != null) ? employee
							.get("fax") : "";
					String employeeOf = (employee.get("employeeOf") != null) ? employee
							.get("employeeOf") : "";

					String username = employee.get("nickName");
					String password = employee.get("password");

					// Update the password
					if (!(password == null || password.isEmpty())) {
						try {
							User user = UserManager.getInstance().getUser(
									username);
							user.setPassword(password);
						} catch (UserNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					VCard vcard = new VCard();
					vcard.setNickName(nickName);
					vcard.setFirstName(firstName);
					vcard.setLastName(lastName);
					vcard.setEmailWork(email);
					vcard.setPhoneWork("work", phone);
					vcard.setField("fax", fax);
					vcard.setField("employeeOf", employeeOf);

					Element el = null;
					try {
						String xml = vcard.toXML();

						el = xmlReader.read(
								new StringReader(vcard.getChildElementXML()))
								.getRootElement();
					} catch (DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						String nickName2 = vcard.getNickName();

						VCardManager.getProvider().deleteVCard(nickName2);
						VCardManager.getInstance().setVCard(nickName2, el);
						// VCardManager.getProvider().createVCard(nickName2,
						// el);
					} catch (AlreadyExistsException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// Deleting all users that are in the list of employees, but
				// not in the list provided in the parameter.
				BlucargoUserProvider cargoUserProvider = new BlucargoUserProvider();
				List<String> existingEmployees = null;
				try {
					existingEmployees = cargoUserProvider
							.getEmployeesOf(employer);
				} catch (UserNotFoundException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}

				// for (String existingEmployee : existingEmployees) {
				//
				// }

				List<String> newEmployeeNames = new ArrayList<String>();
				for (Map<String, String> employee : employees) {
					newEmployeeNames.add(employee.get("nickName"));
				}

				for (String existingEmployee : existingEmployees) {
					if (!newEmployeeNames.contains(existingEmployee)) {
						cargoUserProvider.deleteUser(existingEmployee);
					}
				}

				return null;
			}

		};

		XMPPServer.getInstance().getIQRouter()
				.addHandler(getCargoOffersHandler);
		XMPPServer.getInstance().getIQRouter().addHandler(findOfferByIdHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(removeCargoOffersHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(addCargoOffersHandler);

		XMPPServer.getInstance().getIQRouter()
				.addHandler(getAcceptedOffersHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(getAcceptedOfferByCargoOfferIdAndUserNameHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(addAcceptedOffersHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(removeAcceptedOffersHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(removeAcceptedOffersByCargoOfferAndOwnerHandler);

		XMPPServer.getInstance().getIQRouter().addHandler(getCarBodiesHandler);

		XMPPServer.getInstance().getIQRouter().addHandler(getCountriesHandler);

		XMPPServer.getInstance().getIQRouter().addHandler(getCitiesHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(getCitiesByCountryHandler);

		XMPPServer.getInstance().getIQRouter()
				.addHandler(addFavouriteOffersHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(removeFavouriteOffersHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(getFavouriteOffersHandler);

		XMPPServer.getInstance().getIQRouter()
				.addHandler(addEndedOffersHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(removeEndedOffersHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(getEndedOffersHandler);

		XMPPServer.getInstance().getIQRouter()
				.addHandler(getUserJidFromNickNameHandler);

		XMPPServer.getInstance().getIQRouter()
				.addHandler(initiateOfferAcceptance);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(initiatorLogsOutOfferAcceptance);

		XMPPServer.getInstance().getIQRouter().addHandler(addCommentHandler);
		XMPPServer.getInstance().getIQRouter().addHandler(removeCommentHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(findCommentsByOfferIdHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(findCommentsByAuthorHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(findAllCommentsForUserHandler);
		XMPPServer.getInstance().getIQRouter()
		.addHandler(findAllCommentsForUserSimpleHandler);
		
		XMPPServer.getInstance().getIQRouter()
				.addHandler(addSearchCriteriaHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(removeSearchCriteriaHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(findSearchCriteriasByUserHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(deleteSearchCriteriaByUserNameAndNameHandler);

		XMPPServer.getInstance().getIQRouter()
				.addHandler(getEmployeesOfHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(getUserPropertyHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(setUserPropertyHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(saveOrUpdateUsersHandler);
		XMPPServer.getInstance().getIQRouter()
				.addHandler(getUserVCardPropertyHandler);

		XMPPServer.getInstance().getIQRouter()
				.addHandler(findEndedOfferByOwnerHandler);

		XMPPServer.getInstance().getIQRouter()
				.addHandler(findAllCountryHandler);

		XMPPServer.getInstance().getIQRouter()
				.addHandler(getUserByRegistrationNumberHandler);
		
		XMPPServer.getInstance().getIQRouter()
		.addHandler(isUserHandler);
	}

	public void destroyPlugin() {
		componentManager = null;
		pluginManager = null;
		sessionManager = null;
		groupManager = null;
		allowedUsers.clear();
	}

	static BlucargoPlugin instance;

	public static BlucargoPlugin get() {
		return instance;
	}

	public static void set(BlucargoPlugin inst) {
		instance = inst;
	}

	public RegistrationDataService getRegistrationService() {
		return registrationDataService;
	}

	public void initializePlugin(PluginManager manager, File pluginDirectory) {

		set(this);

		pluginManager = manager;
		sessionManager = SessionManager.getInstance();
		groupManager = GroupManager.getInstance();
		createHandlers();

		Injector injector = Guice.createInjector(new MainPersistenceModule());
		acceptedOfferService = injector.getInstance(AcceptedOfferService.class);
		cargoOfferService = injector.getInstance(CargoOfferService.class);
		carBodyService = injector.getInstance(CarBodyService.class);
		commentService = injector.getInstance(CommentService.class);
		cityService = injector.getInstance(CityService.class);
		countryService = injector.getInstance(CountryService.class);
		favouriteOfferService = injector
				.getInstance(FavouriteOfferService.class);
		endedOfferService = injector.getInstance(EndedOfferService.class);
		offerAcceptanceService = injector
				.getInstance(OfferAcceptanceService.class);
		searchCriteriaService = injector
				.getInstance(SearchCriteriaService.class);
		registrationDataService = injector
				.getInstance(RegistrationDataService.class);

		BlucargoSessionListener cargoSessionListener = injector
				.getInstance(BlucargoSessionListener.class);
		SessionEventDispatcher.addListener(cargoSessionListener);
	}

}