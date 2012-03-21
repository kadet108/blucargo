/**
 * $Revision: $
 * $Date: $
 *
 * Copyright (C) 2006 Jive Software. All rights reserved.
 *
 * This software is published under the terms of the GNU Lesser Public License (LGPL),
 * a copy of which is included in this distribution.
 */

package org.jivesoftware.resource;

import org.jivesoftware.spark.util.log.Log;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class SparkRes {
    public static final String ACCEPT_CHAT = "ACCEPT_CHAT";

    

    
    public static final String ACCEPT_INVITE_IMAGE = "ACCEPT_INVITE_IMAGE";
    public static final String ADD_BOOKMARK_ICON = "ADD_BOOKMARK_ICON";
    public static final String ADD_CONTACT_IMAGE = "ADD_CONTACT_IMAGE";
    public static final String ADD_IMAGE_24x24 = "ADD_IMAGE_24x24";
    public static final String ADD_LINK_TO_CHAT = "ADD_LINK_TO_CHAT";
    public static final String ADD_TO_CHAT = "ADD_TO_CHAT";
    public static final String ADD_TO_KB = "ADD_TO_KB";
    public static final String ADDRESS_BOOK_16x16 = "ADDRESS_BOOK_16x16";
    public static final String AIM_TRANSPORT_ACTIVE_IMAGE = "AIM_TRANSPORT_ACTIVE_IMAGE";
    public static final String AIM_TRANSPORT_INACTIVE_IMAGE = "AIM_TRANSPORT_INACTIVE_IMAGE";
    public static final String ALL_CHATS = "ALL_CHATS";
    public static final String APP_NAME = "APP_NAME";
    public static final String AVAILABLE_USER = "AVAILABLE_USER";
    public static final String AWAY_USER = "AWAY_USER";
    public static final String BACKGROUND_IMAGE = "BACKGROUND_IMAGE";
    public static final String BLANK_24x24 = "BLANK_24x24";
    public static final String BLANK_IMAGE = "BLANK_IMAGE";
    public static final String BLOCK_CONTACT_16x16 = "BLOCK_CONTACT_16x16";
    public static final String BLUE_BALL = "BLUE_BALL";
    public static final String BOOKMARK_ICON = "BOOKMARK_ICON";
    public static final String BRICKWALL_IMAGE = "BRICKWALL_IMAGE";
    public static final String BUSY_IMAGE = "BUSY_IMAGE";
    public static final String BUZZ_IMAGE = "BUZZ_IMAGE";
    public static final String CALENDAR_IMAGE = "CALENDAR_IMAGE";
    public static final String CALL_ICON = "CALL_ICON";

    
    //---------------------------------------------------------------------------
    //   CARGO
    //---------------------------------------------------------------------------

    public static final String CARGO_AKCEPTUJ = "CARGO_AKCEPTUJ";
    public static final String CARGO_ANULUJ = "CARGO_ANULUJ";
    public static final String CARGO_BLANK = "CARGO_BLANK";
    public static final String CARGO_DLUZNICY = "CARGO_DLUZNICY";
    public static final String CARGO_DODAJ_ICO = "CARGO_DODAJ_ICO";
    public static final String CARGO_DODAJ_OFERTE = "CARGO_DODAJ_OFERTE";
    public static final String CARGO_DODAJ_OGLOSZENIE_OFF = "CARGO_DODAJ_OGLOSZENIE_OFF";
    public static final String CARGO_DODAJ_OGLOSZENIE_ON = "CARGO_DODAJ_OGLOSZENIE_ON";
    public static final String CARGO_DOL1 = "CARGO_DOL1";
    public static final String CARGO_DOL2 = "CARGO_DOL2";
    public static final String CARGO_DOLNY_PASEK = "CARGO_DOLNY_PASEK";
    public static final String CARGO_GIELDA = "CARGO_GIELDA";
    public static final String CARGO_GIELDA_OFF = "CARGO_GIELDA_OFF";
    public static final String CARGO_GIELDA_ON = "CARGO_GIELDA_ON";
    public static final String CARGO_GORA1 = "CARGO_GORA1";
    public static final String CARGO_GORA2 = "CARGO_GORA2";
    public static final String CARGO_KOMENTARZ_HEADER = "CARGO_KOMENTARZ_HEADER";
    
    public static final String CARGO_KONTAKT = "CARGO_KONTAKT";
    public static final String CARGO_KONTAKT_Z_KLIENTEM_OFF = "CARGO_KONTAKT_Z_KLIENTEM_OFF";
    public static final String CARGO_KONTAKT_Z_KLIENTEM_ON = "CARGO_KONTAKT_Z_KLIENTEM_ON";
    public static final String CARGO_KONTAKTY = "CARGO_KONTAKTY";
    public static final String CARGO_LADUNEK_ICO = "CARGO_LADUNEK_ICO";
    public static final String CARGO_LOGO_ICO = "CARGO_LOGO_ICO";
    public static final String CARGO_MENU_DLUZNICY_1 = "CARGO_MENU_DLUZNICY_1";
    public static final String CARGO_MENU_DLUZNICY_2 = "CARGO_MENU_DLUZNICY_2";
    public static final String CARGO_MENU_LADUNEK_1 = "CARGO_MENU_LADUNEK_1";
    public static final String CARGO_MENU_LADUNEK_2 = "CARGO_MENU_LADUNEK_2";
    
    public static final String CARGO_MENU_POJAZD_1 = "CARGO_MENU_POJAZD_1";
    public static final String CARGO_MENU_POJAZD_2 = "CARGO_MENU_POJAZD_2";

    public static final String CARGO_MENU_POMOC_1 = "CARGO_MENU_POMOC_1";
    public static final String CARGO_MENU_POMOC_2 = "CARGO_MENU_POMOC_2";
    public static final String CARGO_MENU_RANKING_1 = "CARGO_MENU_RANKING_1";
    public static final String CARGO_MENU_RANKING_2 = "CARGO_MENU_RANKING_2";
    public static final String CARGO_MENU_ROZMOWY_1 = "CARGO_MENU_ROZMOWY_1";
    public static final String CARGO_MENU_ROZMOWY_2 = "CARGO_MENU_ROZMOWY_2";
    public static final String CARGO_MENU_START_1 = "CARGO_MENU_START_1";

    public static final String CARGO_MENU_START_2 = "CARGO_MENU_START_2";
    public static final String CARGO_MENU_USTAWIENIA_1 = "CARGO_MENU_USTAWIENIA_1";
    public static final String CARGO_MENU_USTAWIENIA_2 = "CARGO_MENU_USTAWIENIA_2";
    public static final String CARGO_NASTEPNA_STRONA_OFF = "CARGO_NASTEPNA_STRONA_OFF";

    public static final String CARGO_NASTEPNA_STRONA_ON = "CARGO_NASTEPNA_STRONA_ON";
    public static final String CARGO_NAWIGACJA_NAPISZ_1 = "CARGO_NAWIGACJA_NAPISZ_1";

    
    public static final String CARGO_NAWIGACJA_NAPISZ_2 = "CARGO_NAWIGACJA_NAPISZ_2";
    public static final String CARGO_NAWIGACJA_NASTEPNA_1 = "CARGO_NAWIGACJA_NASTEPNA_1";
    public static final String CARGO_NAWIGACJA_NASTEPNA_2 = "CARGO_NAWIGACJA_NASTEPNA_2";
    public static final String CARGO_NAWIGACJA_OGLOSZENIE_1 = "CARGO_NAWIGACJA_OGLOSZENIE_1";
    public static final String CARGO_NAWIGACJA_OGLOSZENIE_2 = "CARGO_NAWIGACJA_OGLOSZENIE_2";
    public static final String CARGO_NAWIGACJA_POPRZEDNIA_1 = "CARGO_NAWIGACJA_POPRZEDNIA_1";
    
    public static final String CARGO_NAWIGACJA_POPRZEDNIA_2 = "CARGO_NAWIGACJA_POPRZEDNIA_2";
    public static final String CARGO_NAWIGACJA_SZCZEGOLY_1 = "CARGO_NAWIGACJA_SZCZEGOLY_1";
    public static final String CARGO_NAWIGACJA_SZCZEGOLY_2 = "CARGO_NAWIGACJA_SZCZEGOLY_2";
    public static final String CARGO_NEGATYW_OFF = "CARGO_NEGATYW_OFF";
    public static final String CARGO_NEGATYW_ON = "CARGO_NEGATYW_ON";
    public static final String CARGO_NEGATYWNY_HEADER = "CARGO_NEGATYWNY_HEADER";
    public static final String CARGO_NIEBIESKI_PASEK = "CARGO_NIEBIESKI_PASEK";
    public static final String CARGO_OGLOSZENIE_LADUNEK_ICO = "CARGO_OGLOSZENIE_LADUNEK_ICO";
    public static final String CARGO_OGLOSZENIE_POJAZD_ICO = "CARGO_OGLOSZENIE_POJAZD_ICO";
    public static final String CARGO_PASEK_GORA = "CARGO_PASEK_GORA";
    public static final String CARGO_PASEK_MENU_TLO = "CARGO_PASEK_MENU_TLO";
    public static final String CARGO_PASEK_STOPKA_TLO = "CARGO_PASEK_STOPKA_TLO";
    public static final String CARGO_POJAZD = "CARGO_POJAZD";
    public static final String CARGO_POJAZD_ICO = "CARGO_POJAZD_ICO";
    
    public static final String CARGO_POMOC = "CARGO_DLUZNICY";
    public static final String CARGO_POPRZEDNIA_STRONA_OFF = "CARGO_POPRZEDNIA_STRONA_OFF";
    public static final String CARGO_POPRZEDNIA_STRONA_ON = "CARGO_POPRZEDNIA_STRONA_ON";
    public static final String CARGO_POZYTYW_OFF = "CARGO_POZYTYW_OFF";
    public static final String CARGO_POZYTYW_ON = "CARGO_POZYTYW_ON";
    
    public static final String CARGO_POZYTYWNY_HEADER = "CARGO_POZYTYWNY_HEADER";
    public static final String CARGO_RANKING_FIRM = "CARGO_RANKING_FIRM";

    public static final String CARGO_RODZAJ_OGLOSZENIA = "CARGO_RODZAJ_OGLOSZENIA";
    public static final String CARGO_ROZLADUNEK = "CARGO_ROZLADUNEK";
    public static final String CARGO_SZCZEGOLY_OGLOSZENIA_HEADER = "CARGO_SZCZEGOLY_OGLOSZENIA_HEADER";
    public static final String CARGO_SZUKAJ_OSOBE = "CARGO_SZUKAJ_OSOBE";
    public static final String CARGO_TLO1PX = "CARGO_TLO1PX";
    public static final String CARGO_ULUBIONE_OFERTY_OFF = "CARGO_ULUBIONE_OFERTY_OFF";
    public static final String CARGO_ULUBIONE_OFERTY_ON = "CARGO_ULUBIONE_OFERTY_ON";
    public static final String CARGO_USTAWIENIA = "CARGO_USTAWIENIA";
    public static final String CARGO_WAZNE_DO = "CARGO_WAZNE_DO";
    public static final String CARGO_WIADOMOSCI = "CARGO_WIADOMOSCI";
    public static final String CARGO_WYSTAW_KOMENTARZ_OFF = "CARGO_WYSTAW_KOMENTARZ_OFF";
    public static final String CARGO_WYSTAW_KOMENTARZ_ON = "CARGO_WYSTAW_KOMENTARZ_ON";
    public static final String CARGO_ZAAKCEPTOWANE_OFERTY_OFF = "CARGO_ZAAKCEPTOWANE_OFERTY_OFF";
    public static final String CARGO_ZAAKCEPTOWANE_OFERTY_ON = "CARGO_ZAAKCEPTOWANE_OFERTY_ON";
    public static final String CARGO_ZAKONCZONE_OFERTY_OFF = "CARGO_ZAKONCZONE_OFERTY_OFF";
    public static final String CARGO_ZAKONCZONE_OFERTY_ON = "CARGO_ZAKONCZONE_OFERTY_ON";
    public static final String CARGO_ZAKONCZONO_OFF = "CARGO_ZAKONCZONO_OFF";
    public static final String CARGO_ZAKONCZONO_ON = "CARGO_ZAKONCZONO_ON";
    public static final String CARGO_ZALADUNEK = "CARGO_ZALADUNEK";
    public static final String CARGO_ZALOGUJ_SIE_ICO = "CARGO_ZALOGUJ_SIE_ICO";
    public static final String CARGO_ZAREJESTRUJ_SIE_ICO = "CARGO_ZAREJESTRUJ_SIE_ICO";
    public static final String CARGO_ZGLOS_LADUNEK = "CARGO_ZGLOS_LADUNEK";
    public static final String CARGO_ZGLOS_OFERTE = "CARGO_ZGLOS_OFERTE";
    public static final String CARGO_ZGLOS_POJAZD = "CARGO_ZGLOS_POJAZD";
    public static final String CARGO_ZGLOS_LADUNEK_HEADER = "CARGO_ZGLOS_LADUNEK_HEADER";
    public static final String CARGO_ZGLOS_POJAZD_HEADER = "CARGO_ZGLOS_POJAZD_HEADER";
    public static final String CARGO_ZGLOSZONO = "CARGO_ZGLOSZONO";
    public static final String CARGO_POJAZD_HEAD = "CARGO_POJAZD_HEAD";
    public static final String CARGO_LADUNEK_HEAD = "CARGO_LADUNEK_HEAD";
    

    

    public static final String CARGO_SZUKAJKA_SZUKAJ_1 = "CARGO_SZUKAJKA_SZUKAJ_1";
    public static final String CARGO_SZUKAJKA_SZUKAJ_2 = "CARGO_SZUKAJKA_SZUKAJ_2";
    public static final String CARGO_SZUKAJKA_WYCZYSC_1 = "CARGO_SZUKAJKA_WYCZYSC_1";
    public static final String CARGO_SZUKAJKA_WYCZYSC_2 = "CARGO_SZUKAJKA_WYCZYSC_2";
    public static final String CARGO_SZUKAJKA_NOWA_SZUKAJ_1 = "CARGO_SZUKAJKA_NOWA_SZUKAJ_1";
    public static final String CARGO_SZUKAJKA_NOWA_SZUKAJ_2 = "CARGO_SZUKAJKA_NOWA_SZUKAJ_2";
    public static final String CARGO_SZUKAJKA_NOWA_WYCZYSC_1 = "CARGO_SZUKAJKA_NOWA_WYCZYSC_1";
    public static final String CARGO_SZUKAJKA_NOWA_WYCZYSC_2 = "CARGO_SZUKAJKA_NOWA_WYCZYSC_2";
    public static final String CARGO_SZUKAJKA_ZAAWANSOWANE_1 = "CARGO_SZUKAJKA_ZAAWANSOWANE_1";
    public static final String CARGO_SZUKAJKA_ZAAWANSOWANE_2 = "CARGO_SZUKAJKA_ZAAWANSOWANE_2";
    public static final String CARGO_SZUKAJKA_TLO = "CARGO_SZUKAJKA_TLO";
    public static final String CARGO_SZUKAJKA_PUSTA_1 = "CARGO_SZUKAJKA_PUSTA_1";
    public static final String CARGO_SZUKAJKA_PUSTA_2 = "CARGO_SZUKAJKA_PUSTA_2";
    public static final String CARGO_SZUKAJKA_PUSTA_3 = "CARGO_SZUKAJKA_PUSTA_3";
    public static final String CARGO_SZUKAJKA_PUSTA_4 = "CARGO_SZUKAJKA_PUSTA_4";

    
    
    public static final String CARGO_DO_ULUBIONYCH_1 = "CARGO_DO_ULUBIONYCH_1";
    public static final String CARGO_DO_ULUBIONYCH_2 = "CARGO_DO_ULUBIONYCH_2";
    public static final String CARGO_SZCZEGOLY_1 = "CARGO_SZCZEGOLY_1";
    public static final String CARGO_SZCZEGOLY_2 = "CARGO_SZCZEGOLY_2";
    public static final String CARGO_KONTAKT_1 = "CARGO_KONTAKT_1";
    public static final String CARGO_KONTAKT_2 = "CARGO_KONTAKT_2";
    
    public static final String CARGO_USUN_1 = "CARGO_USUN_1";
    public static final String CARGO_USUN_2 = "CARGO_USUN_2";
    public static final String CARGO_KOMENTARZ_1 = "CARGO_KOMENTARZ_1";
    public static final String CARGO_KOMENTARZ_2 = "CARGO_KOMENTARZ_2";
    public static final String CARGO_ZAKONCZONO_1 = "CARGO_ZAKONCZONO_1";
    public static final String CARGO_ZAKONCZONO_2 = "CARGO_ZAKONCZONO_2";
    
    public static final String CARGO_MOJE_ROZMOWY_HEAD1 = "CARGO_MOJE_ROZMOWY_HEAD1";
    public static final String CARGO_MOJE_ROZMOWY_HEAD2 = "CARGO_MOJE_ROZMOWY_HEAD2";
    public static final String CARGO_HISTORIA_ROZMOW1 = "CARGO_HISTORIA_ROZMOW1";
    public static final String CARGO_HISTORIA_ROZMOW2 = "CARGO_HISTORIA_ROZMOW2";
    
    public static final String CARGO_RANKING_HEAD1 = "CARGO_RANKING_HEAD1";
    public static final String CARGO_RANKING_HEAD2 = "CARGO_RANKING_HEAD2";
    
    public static final String CARGO_POMOC_HEAD1 = "CARGO_POMOC_HEAD1";
    public static final String CARGO_POMOC_HEAD2 = "CARGO_POMOC_HEAD2";
    
    
    public static final String LOGOWANIE_ZALOGUJ_1 = "LOGOWANIE_ZALOGUJ_1";
    public static final String LOGOWANIE_ZALOGUJ_2 = "LOGOWANIE_ZALOGUJ_2";
    public static final String LOGOWANIE_ZAREJESTRUJ_1 = "LOGOWANIE_ZAREJESTRUJ_1";
    public static final String LOGOWANIE_ZAREJESTRUJ_2 = "LOGOWANIE_ZAREJESTRUJ_2";
    public static final String LOGOWANIE_PUSTY_1 = "LOGOWANIE_PUSTY_1";
    public static final String LOGOWANIE_PUSTY_2 = "LOGOWANIE_PUSTY_2";
    
    public static final String CARGO_OGLOSZENIE_PUSTY1 = "CARGO_OGLOSZENIE_PUSTY1";
    public static final String CARGO_OGLOSZENIE_PUSTY2 = "CARGO_OGLOSZENIE_PUSTY2";
    
    public static final String CARGO_ZAKLADKI_PUSTE1 = "CARGO_ZAKLADKI_PUSTE1";
    public static final String CARGO_ZAKLADKI_PUSTE2 = "CARGO_ZAKLADKI_PUSTE2";
    
    public static final String CARGO_POPRZEDNIA_PUSTA1 = "CARGO_POPRZEDNIA_PUSTA1";
    public static final String CARGO_POPRZEDNIA_PUSTA2 = "CARGO_POPRZEDNIA_PUSTA2";
    public static final String CARGO_NASTEPNA_PUSTA1 = "CARGO_NASTEPNA_PUSTA1";
    public static final String CARGO_NASTEPNA_PUSTA2 = "CARGO_NASTEPNA_PUSTA2";
    
    public static final String CARGO_KOMENTARZ_HEAD1 = "CARGO_KOMENTARZ_HEAD1";
    public static final String CARGO_KOMENTARZ_BUZIA_POZYTYW = "CARGO_KOMENTARZ_BUZIA_POZYTYW";
    public static final String CARGO_KOMENTARZ_BUZIA_NEGATYW = "CARGO_KOMENTARZ_BUZIA_NEGATYW";
    
    public static final String CARGO_NEGATYW_PUSTY1 = "CARGO_NEGATYW_PUSTY1";
    public static final String CARGO_NEGATYW_PUSTY2 = "CARGO_NEGATYW_PUSTY2";
    public static final String CARGO_POZYTYW_PUSTY1 = "CARGO_POZYTYW_PUSTY1";
    public static final String CARGO_POZYTYW_PUSTY2 = "CARGO_POZYTYW_PUSTY2";
    
    public static final String CARGO_AKCEPTACJA_OFERTY1 = "CARGO_AKCEPTACJA_OFERTY1";
    public static final String CARGO_AKCEPTACJA_OFERTY2 = "CARGO_AKCEPTACJA_OFERTY2";
    
    public static final String CARGO_TEST_FLAGA = "CARGO_TEST_FLAGA";
    
    
    

    
    
    
    


    
    

    

    
    
    
    
    
    
    
    
    
    
   
    
    
    
    
    
    //---------------------------------------------------------------------------
    //   CARGO
    //---------------------------------------------------------------------------
    
    
    
    //---------------------------------------------------------------------------
    //   SPARK ZMIANY GRAFIKI:POCZATEK
    //---------------------------------------------------------------------------
    
    public static final String SPARK_IKONKA_WIZYTOWKA = "SPARK_IKONKA_WIZYTOWKA";
    public static final String SPARK_IKONKA_DODAJ = "SPARK_IKONKA_DODAJ";
    public static final String SPARK_IKONKA_WYSLIJ = "SPARK_IKONKA_WYSLIJ";
    public static final String SPARK_IKONKA_HISTORIA = "SPARK_IKONKA_HISTORIA";
    public static final String SPARK_ZAPROSZENIE_DO_ROZMOWY = "SPARK_ZAPROSZENIE_DO_ROZMOWY";
    public static final String SPARK_CAPTURE_SCREEN = "SPARK_CAPTURE_SCREEN";
    

    
    
    //---------------------------------------------------------------------------
    //   SPARK ZMIANY GRAFIKI:KONIEC
    //---------------------------------------------------------------------------

    
    public static final String CHANGELOG_IMAGE = "CHANGELOG_IMAGE";
    public static final String CHAT_QUEUE = "CHAT_QUEUE";
    public static final String CHAT_WORKSPACE = "CHAT_WORKSPACE";
    public static final String CHATTING_AGENT_IMAGE = "CHATTING_AGENT_IMAGE";
    public static final String CHATTING_CUSTOMER_IMAGE = "CHATTING_CUSTOMER_IMAGE";
    public static final String CIRCLE_CHECK_IMAGE = "CIRCLE_CHECK_IMAGE";
    static ClassLoader cl = SparkRes.class.getClassLoader();
    public static final String CLEAR_BALL_ICON = "CLEAR_BALL_ICON";
    public static final String CLOSE_DARK_X_IMAGE = "CLOSE_DARK_X_IMAGE";
    public static final String CLOSE_IMAGE = "CLOSE_IMAGE";
    public static final String CLOSE_WHITE_X_IMAGE = "CLOSE_WHITE_X_IMAGE";
    public static final String CO_BROWSER_TAB_TITLE = "CO_BROWSER_TAB_TITLE";
    public static final String COMPUTER_IMAGE_16x16 = "COMPUTER_IMAGE_16x16";
    public static final String CONFERENCE_IMAGE_16x16 = "CONFERENCE_IMAGE_16x16";
    public static final String CONFERENCE_IMAGE_24x24 = "CONFERENCE_IMAGE_24x24";
    public static final String CONFERENCE_IMAGE_48x48 = "CONFERENCE_IMAGE_48x48";
    public static final String COPY_16x16 = "COPY_16x16";
    public static final String CREATE_FAQ_ENTRY = "CREATE_FAQ_ENTRY";
    public static final String CREATE_FAQ_TITLE = "CREATE_FAQ_TITLE";
    public static final String CURRENT_AGENTS = "CURRENT_AGENTS";
    public static final String CURRENT_CHATS = "CURRENT_CHATS";
    public static final String DATA_DELETE_16x16 = "DATA_DELETE_16x16";
    public static final String DATA_REFRESH_16x16 = "DATA_REFRESH_16x16";
    public static final String DEFAULT_AVATAR_16x16_IMAGE = "DEFAULT_AVATAR_16x16_IMAGE";
    public static final String DEFAULT_AVATAR_32x32_IMAGE = "DEFAULT_AVATAR_32x32_IMAGE";
    public static final String DEFAULT_AVATAR_64x64_IMAGE = "DEFAULT_AVATAR_64x64_IMAGE";
    public static final String DELETE_BOOKMARK_ICON = "DELETE_BOOKMARK_ICON";
    public static final String DESKTOP_IMAGE = "DESKTOP_IMAGE";
    public static final String DIAL_PHONE_IMAGE_16x16 = "DIAL_PHONE_IMAGE_16x16";
    public static final String DIAL_PHONE_IMAGE_24x24 = "DIAL_PHONE_IMAGE_24x24";
    public static final String DOCUMENT_16x16 = "DOCUMENT_16x16";
    public static final String DOCUMENT_EXCHANGE_IMAGE = "DOCUMENT_EXCHANGE_IMAGE";
    public static final String DOCUMENT_FIND_16x16 = "DOCUMENT_FIND_16x16";
    public static final String DOCUMENT_INFO_32x32 = "DOCUMENT_INFO_32x32";
    public static final String DOOR_IMAGE = "DOOR_IMAGE";
    public static final String DOWN_ARROW_IMAGE = "DOWN_ARROW_IMAGE";
    public static final String DOWN_OPTION_IMAGE = "DOWN_OPTION_IMAGE";
    public static final String DOWNLOAD_16x16 = "DOWNLOAD_16x16";
    public static final String DUMMY_CONTACT_IMAGE = "DUMMY_CONTACT_IMAGE";
    public static final String EARTH_LOCK_16x16 = "EARTH_LOCK_16x16";
    public static final String EARTH_VIEW_16x16 = "EARTH_VIEW_16x16";
    public static final String END_BUTTON_24x24 = "END_BUTTON_24x24";
    public static final String ERASER_IMAGE = "ERASER_IMAGE";
    public static final String ERROR_DIALOG_TITLE = "ERROR_DIALOG_TITLE";
    public static final String ERROR_INVALID_WORKGROUP = "ERROR_INVALID_WORKGROUP";
    public static final String FACEBOOK_TRANSPORT_ACTIVE_IMAGE = "FACEBOOK_TRANSPORT_ACTIVE_IMAGE";
    public static final String FACEBOOK_TRANSPORT_INACTIVE_IMAGE = "FACEBOOK_TRANSPORT_INACTIVE_IMAGE";
    public static final String FAQ_TAB_TITLE = "FAQ_TAB_TITLE";
    public static final String FASTPATH_IMAGE_16x16 = "FASTPATH_IMAGE_16x16";
    public static final String FASTPATH_IMAGE_24x24 = "FASTPATH_IMAGE_24x24";
    public static final String FASTPATH_IMAGE_32x32 = "FASTPATH_IMAGE_32x32";
    public static final String FASTPATH_IMAGE_64x64 = "FASTPATH_IMAGE_64x64";
    public static final String FASTPATH_OFFLINE_IMAGE_16x16 = "FASTPATH_OFFLINE_IMAGE_16x16";
    public static final String FASTPATH_OFFLINE_IMAGE_24x24 = "FASTPATH_OFFLINE_IMAGE_24x24";
    public static final String FIND_IMAGE = "FIND_IMAGE";
    public static final String FIND_TEXT_IMAGE = "FIND_TEXT_IMAGE";
    public static final String FOLDER = "FOLDER";
    public static final String FOLDER_CLOSED = "FOLDER_CLOSED";
    public static final String FONT_16x16 = "FONT_16x16";
    public static final String FORUM_TAB_TITLE = "FORUM_TAB_TITLE";
    public static final String FREE_TO_CHAT_IMAGE = "FREE_TO_CHAT_IMAGE";
    public static final String FUNNEL_DOWN_16x16 = "FUNNEL_DOWN_16x16";
    public static final String GADUGADU_TRANSPORT_ACTIVE_IMAGE = "GADUGADU_TRANSPORT_ACTIVE_IMAGE";
    public static final String GADUGADU_TRANSPORT_INACTIVE_IMAGE = "GADUGADU_TRANSPORT_INACTIVE_IMAGE";
    public static final String GO = "GO";
    public static final String GREEN_BALL = "GREEN_BALL";
    public static final String GREEN_FLAG_16x16 = "GREEN_FLAG_16x16";
    public static final String GTALK_TRANSPORT_ACTIVE_IMAGE = "GTALK_TRANSPORT_ACTIVE_IMAGE";
    public static final String GTALK_TRANSPORT_INACTIVE_IMAGE = "GTALK_TRANSPORT_INACTIVE_IMAGE";
    public static final String HEADSET_IMAGE = "HEADSET_IMAGE";
    public static final String HELP2_24x24 = "HELP2_24x24";
    public static final String HISTORY_16x16 = "HISTORY_16x16";
    public static final String HISTORY_24x24_IMAGE = "HISTORY_24x24";
    public static final String ICQ_TRANSPORT_ACTIVE_IMAGE = "ICQ_TRANSPORT_ACTIVE_IMAGE";
    public static final String ICQ_TRANSPORT_INACTIVE_IMAGE = "ICQ_TRANSPORT_INACTIVE_IMAGE";
    public static final String ID_CARD_48x48 = "ID_CARD_48x48";
    public static final String IM_AVAILABLE_STALE_IMAGE = "IM_AVAILABLE_STALE_IMAGE";
    public static final String IM_AWAY = "IM_AWAY";
    public static final String IM_AWAY_STALE_IMAGE = "IM_AWAY_STALE_IMAGE";
    public static final String IM_DND = "IM_DND";
    public static final String IM_DND_STALE_IMAGE = "IM_AWAY_STALE_IMAGE";
    public static final String IM_FREE_CHAT_STALE_IMAGE = "IM_FREE_CHAT_STALE_IMAGE";
    public static final String IM_UNAVAILABLE_STALE_IMAGE = "IM_UNAVAILABLE_STALE_IMAGE";
    public static final String INFORMATION_ICO = "INFORMATION_ICO";
    public static final String INFORMATION_IMAGE = "INFORMATION_IMAGE";
    public static final String INVALID_USERNAME_PASSWORD = "INVALID_USERNAME_PASSWORD";
    public static final String INVITE_MORE_IMAGE = "INVITE_MORE_IMAGE";
    public static final String IRC_TRANSPORT_ACTIVE_IMAGE = "IRC_TRANSPORT_ACTIVE_IMAGE";
    public static final String IRC_TRANSPORT_INACTIVE_IMAGE = "IRC_TRANSPORT_INACTIVE_IMAGE";
    public static final String JOIN_GROUPCHAT_IMAGE = "JOIN_GROUPCHAT_IMAGE";
    public static final String KNOWLEDGE_BASE_TAB_TITLE = "KNOWLEDGE_BASE_TAB_TITLE";
    public static final String LEFT_ARROW_IMAGE = "LEFT_ARROW_IMAGE";
    public static final String LIGHTBULB_ON_16x16_IMAGE = "LIGHTBULB_ON_16x16_IMAGE";
    public static final String LIGHTING_BOLT_IMAGE = "LIGHTING_BOLT_IMAGE";
    public static final String LINK_16x16 = "LINK_16x16";
    public static final String LINK_DELETE_16x16 = "LINK_DELETE_16x16";
    public static final String LOCK_16x16 = "LOCK_16x16";
    public static final String LOCK_UNLOCK_16x16 = "LOCK_UNLOCK_16x16";
    public static final String LOGIN_DIALOG_AUTHENTICATING = "LOGIN_DIALOG_AUTHENTICATING";
    public static final String LOGIN_DIALOG_LOGIN = "LOGIN_DIALOG_LOGIN";
    public static final String LOGIN_DIALOG_LOGIN_TITLE = "LOGIN_DIALOG_LOGIN_TITLE";
    public static final String LOGIN_DIALOG_PASSWORD = "LOGIN_DIALOG_PASSWORD";
    public static final String LOGIN_DIALOG_QUIT = "LOGIN_DIALOG_QUIT";
    public static final String LOGIN_DIALOG_USERNAME = "LOGIN_DIALOG_USERNAME";
    public static final String LOGIN_DIALOG_WORKSPACE = "LOGIN_DIALOG_WORKSPACE";
    public static final String LOGIN_KEY_IMAGE = "LOGIN_KEY_IMAGE";
    public static final String MAGICIAN_IMAGE = "MAGICIAN_IMAGE";
    public static final String MAIL_16x16 = "MAIL_16x16";
    public static final String MAIL_FORWARD_16x16 = "MAIL_FORWARD_16x16";
    public static final String MAIL_IMAGE_32x32 = "MAIL_IMAGE_32x32";
    public static final String MAIL_INTO_16x16 = "MAIL_INTO_16x16";
    public static final String MAIN_ICNS_FILE = "MAIN_ICNS_FILE";
    public static final String MAIN_IMAGE = "MAIN_IMAGE";
    public static final String MAIN_IMAGE_ICO = "MAIN_IMAGE_ICO";
    public static final String MAIN_TITLE = "MAIN_TITLE";
    public static final String MEGAPHONE_16x16 = "MEGAPHONE_16x16";
    public static final String MESSAGE_AWAY = "MESSAGE_AWAY";
    public static final String MESSAGE_DND = "MESSAGE_DND";
    public static final String MESSAGE_NEW_TRAY = "MESSAGE_NEW_TRAY";
    public static final String MESSAGE_NEW_TRAY_LINUX = "MESSAGE_NEW_TRAY_LINUX";
    public static final String MINUS_SIGN = "MINUS_SIGN";
    public static final String MOBILE_PHONE_IMAGE = "MOBILE_PHONE_IMAGE";
    public static final String MODERATOR_IMAGE = "MODERATOR_IMAGE";
    public static final String MSN_TRANSPORT_ACTIVE_IMAGE = "MSN_TRANSPORT_ACTIVE_IMAGE";
    public static final String MSN_TRANSPORT_INACTIVE_IMAGE = "MSN_TRANSPORT_INACTIVE_IMAGE";
    public static final String MYSPACE_TRANSPORT_ACTIVE_IMAGE = "MYSPACE_TRANSPORT_ACTIVE_IMAGE";
    public static final String MYSPACE_TRANSPORT_INACTIVE_IMAGE = "MYSPACE_TRANSPORT_INACTIVE_IMAGE";
    public static final String NOTE_EDIT_16x16 = "NOTE_EDIT_16x16";
    public static final String NOTEBOOK_IMAGE = "NOTEBOOK_IMAGE";
    public static final String OFFLINE_ICO = "OFFLINE_ICO";
    public static final String OFFLINE_IMAGE = "OFFLINE_IMAGE";
    public static final String ON_PHONE_IMAGE = "ON_PHONE_IMAGE";
    public static final String ONLINE_ICO = "ONLINE_ICO";
    public static final String PALETTE_24x24_IMAGE = "PALETTE_24x24_IMAGE";
    public static final String PANE_DOWN_ARROW_IMAGE = "PANE_DOWN_ARROW_IMAGE";
    public static final String PANE_UP_ARROW_IMAGE = "PANE_UP_ARROW_IMAGE";
    public static final String PAWN_GLASS_GREEN = "PAWN_GLASS_GREEN";
    public static final String PAWN_GLASS_RED = "PAWN_GLASS_RED";
    public static final String PAWN_GLASS_WHITE = "PAWN_GLASS_WHITE";
    public static final String PAWN_GLASS_YELLOW = "PAWN_GLASS_YELLOW";
    public static final String PHOTO_IMAGE = "PHOTO_IMAGE";
    public static final String PLUGIN_IMAGE = "PLUGIN_IMAGE";
    public static final String PLUS_SIGN = "PLUS_SIGN";
    public static final String POWERED_BY_IMAGE = "POWERED_BY_IMAGE";
    private static PropertyResourceBundle prb;
    public static final String PREFERENCES_IMAGE = "PREFERENCES_IMAGE";
    public static final String PRINTER_IMAGE_16x16 = "PRINTER_IMAGE_16x16";
    public static final String PROFILE_ICON = "PROFILE_ICON";
    public static final String PROFILE_IMAGE_16x16 = "PROFILE_IMAGE_16x16";
    public static final String PROFILE_IMAGE_24x24 = "PROFILE_IMAGE_24x24";
    public static final String PROFILE_TAB_TITLE = "PROFILE_TAB_TITLE";
    public static final String PUSH_URL_16x16 = "PUSH_URL_16x16";
    public static final String QQ_TRANSPORT_ACTIVE_IMAGE = "QQ_TRANSPORT_ACTIVE_IMAGE";
    public static final String QQ_TRANSPORT_INACTIVE_IMAGE = "QQ_TRANSPORT_INACTIVE_IMAGE";
    public static final String QUESTIONS_ANSWERS = "QUESTIONS_ANSWERS";
    public static final String README_IMAGE = "README_IMAGE";
    public static final String RED_BALL = "RED_BALL";
    public static final String RED_FLAG_16x16 = "RED_FLAG_16x16";
    public static final String REFRESH_IMAGE = "REFRESH_IMAGE";
    public static final String REJECT_CHAT = "REJECT_CHAT";
    public static final String REJECT_INVITE_IMAGE = "REJECT_INVITE_IMAGE";
    public static final String RIGHT_ARROW_IMAGE = "RIGHT_ARROW_IMAGE";
    public static final String SAMETIME_TRANSPORT_ACTIVE_IMAGE = "SAMETIME_TRANSPORT_ACTIVE_IMAGE";
    public static final String SAMETIME_TRANSPORT_INACTIVE_IMAGE = "SAMETIME_TRANSPORT_INACTIVE_IMAGE";
    public static final String SAVE_AS_16x16 = "SAVE_AS_16x16";
    public static final String SEARCH = "SEARCH";
    public static final String SEARCH_IMAGE_32x32 = "SEARCH_IMAGE_32x32";
    public static final String SEARCH_USER_16x16 = "SEARCH_USER_16x16";
    public static final String SEND = "SEND";
    public static final String SEND_FILE_24x24 = "SEND_FILE_24x24";
    public static final String SEND_FILE_ICON = "SEND_FILE_ICON";
    public static final String SEND_MAIL_IMAGE_16x16 = "SEND_MAIL_IMAGE_16x16";
    public static final String SERVER_ICON = "SERVER_ICON";
    public static final String SERVER_UNAVAILABLE = "SERVER_UNAVAILABLE";
    public static final String SIMPLE_TRANSPORT_ACTIVE_IMAGE = "SIMPLE_TRANSPORT_ACTIVE_IMAGE";
    public static final String SIMPLE_TRANSPORT_INACTIVE_IMAGE = "SIMPLE_TRANSPORT_INACTIVE_IMAGE";
    public static final String SMALL_ABOUT_IMAGE = "SMALL_ABOUT_IMAGE";
    public static final String SMALL_ADD_IMAGE = "SMALL_ADD_IMAGE";
    public static final String SMALL_AGENT_IMAGE = "SMALL_AGENT_IMAGE";
    public static final String SMALL_ALARM_CLOCK = "SMALL_ALARM_CLOCK";
    public static final String SMALL_ALL_AGENTS_IMAGE = "SMALL_ALL_AGENTS_IMAGE";
    public static final String SMALL_ALL_CHATS_IMAGE = "SMALL_ALL_CHATS_IMAGE";
    public static final String SMALL_BUSINESS_MAN_VIEW = "SMALL_BUSINESS_MAN_VIEW";
    public static final String SMALL_CHECK = "SMALL_CHECK";
    public static final String SMALL_CIRCLE_DELETE = "SMALL_CIRCLE_DELETE";
    public static final String SMALL_CLOSE_BUTTON = "SMALL_CLOSE_BUTTON";
    public static final String SMALL_CURRENT_AGENTS = "SMALL_CURRENT_AGENTS";
    public static final String SMALL_DATA_FIND_IMAGE = "SMALL_DATA_FIND_IMAGE";
    public static final String SMALL_DELETE = "SMALL_DELETE";
    public static final String SMALL_DOCUMENT_ADD = "SMALL_DOCUMENT_ADD";
    public static final String SMALL_DOCUMENT_VIEW = "SMALL_DOCUMENT_VIEW";
    public static final String SMALL_ENTRY = "SMALL_ENTRY";
    public static final String SMALL_MESSAGE_EDIT_IMAGE = "SMALL_MESSAGE_EDIT_IMAGE";
    public static final String SMALL_MESSAGE_IMAGE = "SMALL_MESSAGE_IMAGE";
    public static final String SMALL_PIN_BLUE = "SMALL_PIN_BLUE";
    public static final String SMALL_PROFILE_IMAGE = "SMALL_PROFILE_IMAGE";
    public static final String SMALL_QUESTION = "SMALL_QUESTION";
    public static final String SMALL_SCROLL_REFRESH = "SMALL_SCROLL_REFRESH";
    public static final String SMALL_USER_DELETE = "SMALL_USER_DELETE";
    public static final String SMALL_USER_ENTER = "SMALL_USER_ENTER";
    public static final String SMALL_USER1_INFORMATION = "SMALL_USER1_INFORMATION";
    public static final String SMALL_USER1_MESSAGE = "SMALL_USER1_MESSAGE";
    public static final String SMALL_USER1_MOBILEPHONE = "SMALL_USER1_MOBILEPHONE";
    public static final String SMALL_USER1_NEW = "SMALL_USER1_NEW";
    public static final String SMALL_USER1_STOPWATCH = "SMALL_USER1_STOPWATCH";
    public static final String SMALL_USER1_TIME = "SMALL_USER1_TIME";
    public static final String SMALL_WORKGROUP_QUEUE_IMAGE = "SMALL_WORKGROUP_QUEUE_IMAGE";
    public static final String SOUND_PREFERENCES_IMAGE = "SOUND_PREFERENCES_IMAGE";
    public static final String SPARK_IMAGE = "SPARK_IMAGE";
    public static final String SPARK_IMAGE_32x32 = "SPARK_IMAGE_32x32";
    public static final String SPARK_LOGOUT_IMAGE = "SPARK_LOGOUT_IMAGE";
    public static final String SPELL_CHECK_IMAGE = "SPELL_CHECK_IMAGE";
    public static final String STAR_BLUE_IMAGE = "STAR_BLUE_IMAGE";
    public static final String STAR_GREEN_IMAGE = "STAR_GREEN_IMAGE";
    public static final String STAR_GREY_IMAGE = "STAR_GREY_IMAGE";
    public static final String STAR_RED_IMAGE = "STAR_RED_IMAGE";
    public static final String STAR_YELLOW_IMAGE = "STAR_YELLOW_IMAGE";
    public static final String STICKY_NOTE_IMAGE = "STICKY_NOTE_IMAGE";
    public static final String TASK_DELETE_IMAGE = "TASK_DELETE_IMAGE";
    public static final String TELEPHONE_24x24 = "TELEPHONE_24x24";
    public static final String TEXT_BOLD = "TEXT_BOLD";
    public static final String TEXT_ITALIC = "TEXT_ITALIC";
    public static final String TEXT_NORMAL = "TEXT_NORMAL";
    public static final String TEXT_UNDERLINE = "TEXT_UNDERLINE";
    public static final String TIME_LEFT = "TIME_LEFT";
    public static final String TOOLBAR_BACKGROUND = "TOOLBAR_BACKGROUND";
    public static final String TOOLBOX = "TOOLBOX";
    public static final String TRAFFIC_LIGHT_IMAGE = "TRAFFIC_LIGHT_IMAGE";
    public static final String TRANSFER_IMAGE_24x24 = "TRANSFER_IMAGE_24x24";
    public static final String TRAY_AWAY = "TRAY_AWAY";
    public static final String TRAY_AWAY_LINUX = "TRAY_AWAY_LINUX";
    public static final String TRAY_CONNECTING = "TRAY_CONNECTING";
    public static final String TRAY_CONNECTING_LINUX = "TRAY_CONNECTING_LINUX";
    public static final String TRAY_DND = "TRAY_DND";
    public static final String TRAY_DND_LINUX = "TRAY_DND_LINUX";
    public static final String TRAY_IMAGE = "TRAY_IMAGE";
    public static final String TRAY_IMAGE_LINUX = "TRAY_IMAGE_LINUX";
    public static final String TRAY_OFFLINE = "TRAY_OFFLINE";
    public static final String TRAY_OFFLINE_LINUX = "TRAY_OFFLINE_LINUX";
    public static final String TYPING_TRAY = "TYPING_TRAY";
    public static final String TYPING_TRAY_LINUX = "TYPING_TRAY_LINUX";
    public static final String UNBLOCK_CONTACT_16x16 = "UNBLOCK_CONTACT_16x16";
    public static final String UNRECOVERABLE_ERROR = "UNRECOVERABLE_ERROR";
    public static final String USER_HEADSET_24x24 = "USER_HEADSET_24x24";
    public static final String USER1_32x32 = "USER1_32x32";
    public static final String USER1_ADD_16x16 = "USER1_ADD_16x16";    
    public static final String USER1_BACK_16x16 = "USER1_BACK_16x16";
    public static final String USER1_MESSAGE_24x24 = "USER1_MESSAGE_24x24";
    public static final String VERSION = "VERSION";
    public static final String VIEW = "VIEW";
    public static final String VIEW_IMAGE = "VIEW_IMAGE";
    public static final String WELCOME = "WELCOME";
    public static final String WORKGROUP_QUEUE = "WORKGROUP_QUEUE";
    public static final String XMPP_TRANSPORT_ACTIVE_IMAGE = "XMPP_TRANSPORT_ACTIVE_IMAGE";
    public static final String XMPP_TRANSPORT_INACTIVE_IMAGE = "XMPP_TRANSPORT_INACTIVE_IMAGE";
    public static final String YAHOO_TRANSPORT_ACTIVE_IMAGE = "YAHOO_TRANSPORT_ACTIVE_IMAGE";
    public static final String YAHOO_TRANSPORT_INACTIVE_IMAGE = "YAHOO_TRANSPORT_INACTIVE_IMAGE";
    public static final String YELLOW_BALL = "YELLOW_BALL";
    
    public static final String YELLOW_FLAG_16x16 = "YELLOW_FLAG_16x16";

    static {
        prb = (PropertyResourceBundle)ResourceBundle.getBundle("org/jivesoftware/resource/spark");
    }

    private static void checkImageDir() {
        File[] files = new File("c:\\code\\liveassistant\\client\\resources\\images").listFiles();
        final int no = files != null ? files.length : 0;
        for (int i = 0; i < no; i++) {
            try {
                File imageFile = files[i];
                String name = imageFile.getName();

                // Check to see if the name of the file exists
                boolean exists = false;
                Enumeration<String> enumeration = prb.getKeys();
                while (enumeration.hasMoreElements()) {
                    String token = (String)enumeration.nextElement();
                    String value = prb.getString(token);
                    if (value.endsWith(name)) {
                        exists = true;
                    }
                }

                if (!exists) {
                    System.out.println(imageFile.getAbsolutePath() + " is not used.");
                }
            }
            catch (NullPointerException e) {
                // TODO: Should we worry about this?
            }
        }
    }

    public static ImageIcon getImageIcon(String imageName) {
        try {
            final String iconURI = getString(imageName);
            final URL imageURL = cl.getResource(iconURI);
            return new ImageIcon(imageURL);
        }
        catch (Exception ex) {
            System.out.println(imageName + " not found.");
        }
        return null;
    }

    public static String getString(String propertyName) {
        return prb.getString(propertyName);
    }

    public static URL getURL(String propertyName) {
        return cl.getResource(getString(propertyName));
    }

    public static URL getURLWithoutException(String propertyName) {
        // Otherwise, load and add to cache.
        try {
            final String iconURI = getString(propertyName);
            return cl.getResource(iconURI);
        }
        catch (Exception ex) {
            Log.debug(propertyName + " not found.");
        }
        return null;
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout());

        JEditorPane pane = new JEditorPane();
        frame.getContentPane().add(new JScrollPane(pane));

        StringBuffer buf = new StringBuffer();
        Enumeration<String> enumeration = prb.getKeys();
        while (enumeration.hasMoreElements()) {
            String token = enumeration.nextElement();
            String value = prb.getString(token).toLowerCase();
            if (value.endsWith(".gif") || value.endsWith(".png") || value.endsWith(".jpg") || value.endsWith("jpeg")) {
                SparkRes.getImageIcon(token);
            }
            String str = "public static final String " + token + " = \"" + token + "\";\n";
            buf.append(str);
        }

        checkImageDir();
        pane.setText(buf.toString());
        frame.pack();
        frame.setVisible(true);
    }
}
