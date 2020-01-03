<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the
 * installation. You don't have to use the web site, you can
 * copy this file to "wp-config.php" and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * MySQL settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://codex.wordpress.org/Editing_wp-config.php
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'id12007017_wp_441a8a1e80ce9d07ea226710a71c0e82' );

/** MySQL database username */
define( 'DB_USER', 'id12007017_wp_441a8a1e80ce9d07ea226710a71c0e82' );

/** MySQL database password */
define( 'DB_PASSWORD', 'e351c201a4b777bace2b551c9ee6408db3b95394' );

/** MySQL hostname */
define( 'DB_HOST', 'localhost' );

/** Database Charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8' );

/** The Database Collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',          '<6D1KW]Y 2u);VgV&OdRz4$wNf~cu~D>7iZ^nncjgYZovO{N%, 9i-e@pzQHXN{F' );
define( 'SECURE_AUTH_KEY',   '<`xTrW$TJnz$(?SRcni~,UixsgvCG=w&9Wh8hL& Z]`&-mV54,3PY/1u :zE#X%+' );
define( 'LOGGED_IN_KEY',     ')SMd<bOA:pdY-h;jIO7&7@q}ak^F>yDOedvI@^qIL{pOx9#t9sC@BEE- fX]O0>c' );
define( 'NONCE_KEY',         'BkFQDm|FN[^c8o}^aS?A$R/HQ;M$ $nSJ!4MKCSe`kXPgWZiwPd ipUi^.3[7.l_' );
define( 'AUTH_SALT',         '8al0I@{doFJGo{9ic(5JAB)+F~!bQ*LOx_nM8obhI{qDjs,(a(=sc2+)v;`yK8u^' );
define( 'SECURE_AUTH_SALT',  ',O!h;c9J*kKNeIj7)Fjr+.D$u(lR8cG0THmH3Z&=&f%S^F-,=S7kR5IcD_/Ki0`{' );
define( 'LOGGED_IN_SALT',    '}gT`S7/64iFGA<uaaUcSdW5tz.eg3LPs{[@JqNS<Pw(c?Y(Z>XibeufFjJFY~&Qe' );
define( 'NONCE_SALT',        'Gq Bm9a7),&U]j[agg4)h(!F1B[OB47MQxA%vu#VHwj!Jon}v[_+;X9.4/*v9.t4' );
define( 'WP_CACHE_KEY_SALT', 'j~HOtJ2}t>Um>(.7&i66*Y4*=&HE!tQ9|[6]T?)-_U7mZY,?$ lv}(j5&FU>jxN5' );

/**
 * WordPress Database Table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';




/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', dirname( __FILE__ ) . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';
