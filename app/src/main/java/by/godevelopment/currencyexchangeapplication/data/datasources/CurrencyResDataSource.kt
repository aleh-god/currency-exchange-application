package by.godevelopment.currencyexchangeapplication.data.datasources

import by.godevelopment.currencyexchangeapplication.R

object CurrencyResDataSource {

    private val base: List<String> = listOf(
        "USD",
        "EUR",
        "CHF",
        "HRK",
        "MXN",
        "ZAR",
        "CNY",
        "THB",
        "AUD",
        "ILS",
        "KRW",
        "JPY",
        "PLN",
        "GBP",
        "IDR",
        "HUF",
        "PHP",
        "TRY",
        "RUB",
        "HKD",
        "ISK",
        "DKK",
        "CAD",
        "MYR",
        "BGN",
        "NOK",
        "RON",
        "SGD",
        "CZK",
        "SEK",
        "NZD",
        "BRL",
        "INR"
    ).sorted()
    private val stringRes: List<Int> = listOf(
        R.string.currency_aud_name,
        R.string.currency_bgn_name,
        R.string.currency_brl_name,
        R.string.currency_cad_name,
        R.string.currency_chf_name,
        R.string.currency_cny_name,
        R.string.currency_czk_name,
        R.string.currency_dkk_name,
        R.string.currency_eur_name,
        R.string.currency_gbp_name,
        R.string.currency_hkd_name,
        R.string.currency_hrk_name,
        R.string.currency_huf_name,
        R.string.currency_idr_name,
        R.string.currency_ils_name,
        R.string.currency_inr_name,
        R.string.currency_isk_name,
        R.string.currency_jpy_name,
        R.string.currency_krw_name,
        R.string.currency_mxn_name,
        R.string.currency_myr_name,
        R.string.currency_nok_name,
        R.string.currency_nzd_name,
        R.string.currency_php_name,
        R.string.currency_pln_name,
        R.string.currency_ron_name,
        R.string.currency_rub_name,
        R.string.currency_sek_name,
        R.string.currency_sgd_name,
        R.string.currency_thb_name,
        R.string.currency_try_name,
        R.string.currency_usd_name,
        R.string.currency_zar_name
    )

    private val drawableRes: List<Int> = listOf(
        R.drawable.ic_aud_flag,
        R.drawable.ic_bgn_flag,
        R.drawable.ic_brl_flag,
        R.drawable.ic_cad_flag,
        R.drawable.ic_chf_flag,
        R.drawable.ic_cny_flag,
        R.drawable.ic_czk_flag,
        R.drawable.ic_dkk_flag,
        R.drawable.ic_eur_flag,
        R.drawable.ic_gbp_flag,
        R.drawable.ic_hkd_flag,
        R.drawable.ic_hrk_flag,
        R.drawable.ic_huf_flag,
        R.drawable.ic_idr_flag,
        R.drawable.ic_ils_flag,
        R.drawable.ic_inr_flag,
        R.drawable.ic_isk_flag,
        R.drawable.ic_jpy_flag,
        R.drawable.ic_krw_flag,
        R.drawable.ic_mxn_flag,
        R.drawable.ic_myr_flag,
        R.drawable.ic_nok_flag,
        R.drawable.ic_nzd_flag,
        R.drawable.ic_php_flag,
        R.drawable.ic_pln_flag,
        R.drawable.ic_ron_flag,
        R.drawable.ic_rub_flag,
        R.drawable.ic_sek_flag,
        R.drawable.ic_sgd_flag,
        R.drawable.ic_thb_flag,
        R.drawable.ic_try_flag,
        R.drawable.ic_usd_flag,
        R.drawable.ic_zar_flag
    )

    private val currencyName: Map<String, Int> = base
        .mapIndexed { index, string ->
            string to stringRes[index]
        }
        .associate {
            it.first to it.second
        }

    private val currencyDraw: Map<String, Int> = base
        .mapIndexed { index, string ->
            string to drawableRes[index]
        }
        .associate {
            it.first to it.second
        }

    fun getCurrencyNameOrDefaultBy(base: String?): Int =
        currencyName[base] ?: R.string.message_error_data_load

    fun getCurrencyDrawOrDefaultBy(base: String?): Int =
        currencyDraw[base] ?: R.drawable.ic_launcher_background
}
